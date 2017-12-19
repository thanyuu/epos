package com.android.common.service.impl;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.android.common.entity.CacheObject;
import com.android.common.utils.ListUtils;
import com.android.common.utils.ObjectUtils;
import com.android.common.utils.SerializeUtils;
import com.android.common.utils.SystemUtils;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PreloadDataCache<K, V> extends SimpleCache<K, V> {
    public static final int DEFAULT_BACKWARD_CACHE_NUMBER = 1;
    public static final int DEFAULT_FORWARD_CACHE_NUMBER = 3;
    public static final int DEFAULT_THREAD_POOL_SIZE = SystemUtils.getDefaultThreadPoolSize(8);
    public static final int NETWORK_MOBILE = 1;
    public static final int NETWORK_WIFI = 2;
    private static final long serialVersionUID = 1;
    private int allowedNetworkTypes;
    private int backwardCacheNumber;
    private transient ConnectivityManager connectivityManager;
    private Context context;
    private int forwardCacheNumber;
    private transient Map<K, GetDataThread> gettingDataThreadMap;
    private boolean isCheckNetwork;
    protected OnGetDataListener<K, V> onGetDataListener;
    private ExecutorService threadPool;

    private class GetDataThread implements Runnable {
        public CountDownLatch finishGetDataLock = new CountDownLatch(1);
        private K key;
        private OnGetDataListener<K, V> onGetDataListener;

        public GetDataThread(K key, OnGetDataListener<K, V> onGetDataListener) {
            this.key = key;
            this.onGetDataListener = onGetDataListener;
        }

        public void run() {
            if (!(this.key == null || this.onGetDataListener == null)) {
                CacheObject<V> object = this.onGetDataListener.onGetData(this.key);
                if (object != null) {
                    PreloadDataCache.this.put(this.key, (CacheObject) object);
                }
            }
            this.finishGetDataLock.countDown();
            if (PreloadDataCache.this.gettingDataThreadMap != null && this.key != null) {
                PreloadDataCache.this.gettingDataThreadMap.remove(this.key);
            }
        }
    }

    public interface OnGetDataListener<K, V> extends Serializable {
        CacheObject<V> onGetData(K k);
    }

    public CacheObject<V> get(K key, List<K> keyList) {
        if (key == null) {
            return null;
        }
        if (!ListUtils.isEmpty(keyList)) {
            preloadDataForward(key, keyList, this.forwardCacheNumber);
            preloadDataBackward(key, keyList, this.backwardCacheNumber);
        }
        return get(key);
    }

    public CacheObject<V> get(K key) {
        if (key == null) {
            return null;
        }
        CacheObject<V> object = super.get(key);
        if (object != null || this.onGetDataListener == null) {
            return object;
        }
        GetDataThread getDataThread = gettingData(key);
        if (getDataThread != null) {
            try {
                getDataThread.finishGetDataLock.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        object = super.get(key);
        if (object != null) {
            this.hitCount.decrementAndGet();
            return object;
        }
        this.missCount.decrementAndGet();
        return object;
    }

    CacheObject<V> getFromCache(K key) {
        return super.get(key);
    }

    CacheObject<V> getFromCache(K key, List<K> keyList) {
        if (key == null) {
            return null;
        }
        if (!ListUtils.isEmpty(keyList)) {
            preloadDataForward(key, keyList, this.forwardCacheNumber);
            preloadDataBackward(key, keyList, this.backwardCacheNumber);
        }
        return getFromCache(key);
    }

    protected int preloadDataForward(K key, List<K> keyList, int cacheCount) {
        int gettingDataCount = 0;
        if (!(key == null || ListUtils.isEmpty(keyList) || this.onGetDataListener == null)) {
            int cachedCount = 0;
            boolean beginCount = false;
            for (int i = 0; i < keyList.size() && cachedCount <= cacheCount; i++) {
                K k = keyList.get(i);
                if (ObjectUtils.isEquals(k, key)) {
                    beginCount = true;
                } else if (k != null && beginCount) {
                    cachedCount++;
                    if (gettingData(k) != null) {
                        gettingDataCount++;
                    }
                }
            }
        }
        return gettingDataCount;
    }

    protected int preloadDataBackward(K key, List<K> keyList, int cacheCount) {
        int gettingDataCount = 0;
        if (!(key == null || ListUtils.isEmpty(keyList) || this.onGetDataListener == null)) {
            int cachedCount = 0;
            boolean beginCount = false;
            for (int i = keyList.size() - 1; i >= 0 && cachedCount <= cacheCount; i--) {
                K k = keyList.get(i);
                if (ObjectUtils.isEquals(k, key)) {
                    beginCount = true;
                } else if (k != null && beginCount) {
                    cachedCount++;
                    if (gettingData(k) != null) {
                        gettingDataCount++;
                    }
                }
            }
        }
        return gettingDataCount;
    }

    private synchronized GetDataThread gettingData(K key) {
        GetDataThread getDataThread;
        if (containsKey(key) || (this.isCheckNetwork && !checkIsNetworkTypeAllowed())) {
            getDataThread = null;
        } else if (isExistGettingDataThread(key)) {
            getDataThread = (GetDataThread) this.gettingDataThreadMap.get(key);
        } else {
            GetDataThread getDataThread2 = new GetDataThread(key, this.onGetDataListener);
            this.gettingDataThreadMap.put(key, getDataThread2);
            this.threadPool.execute(getDataThread2);
            getDataThread = getDataThread2;
        }
        return getDataThread;
    }

    public synchronized boolean isExistGettingDataThread(K key) {
        return this.gettingDataThreadMap.containsKey(key);
    }

    public PreloadDataCache() {
        this(64, DEFAULT_THREAD_POOL_SIZE);
    }

    public PreloadDataCache(int maxSize) {
        this(maxSize, DEFAULT_THREAD_POOL_SIZE);
    }

    public PreloadDataCache(int maxSize, int threadPoolSize) {
        super(maxSize);
        this.forwardCacheNumber = 3;
        this.backwardCacheNumber = 1;
        this.isCheckNetwork = true;
        this.allowedNetworkTypes = -1;
        this.gettingDataThreadMap = new HashMap();
        if (threadPoolSize <= 0) {
            throw new IllegalArgumentException("The threadPoolSize of cache must be greater than 0.");
        }
        this.threadPool = Executors.newFixedThreadPool(threadPoolSize);
    }

    public int getForwardCacheNumber() {
        return this.forwardCacheNumber;
    }

    public void setForwardCacheNumber(int forwardCacheNumber) {
        this.forwardCacheNumber = forwardCacheNumber;
    }

    public int getBackwardCacheNumber() {
        return this.backwardCacheNumber;
    }

    public void setBackwardCacheNumber(int backwardCacheNumber) {
        this.backwardCacheNumber = backwardCacheNumber;
    }

    public OnGetDataListener<K, V> getOnGetDataListener() {
        return this.onGetDataListener;
    }

    public void setOnGetDataListener(OnGetDataListener<K, V> onGetDataListener) {
        this.onGetDataListener = onGetDataListener;
    }

    public int getAllowedNetworkTypes() {
        return this.allowedNetworkTypes;
    }

    public void setAllowedNetworkTypes(int allowedNetworkTypes) {
        this.allowedNetworkTypes = allowedNetworkTypes;
    }

    public boolean isCheckNetwork() {
        return this.isCheckNetwork;
    }

    public void setCheckNetwork(boolean isCheckNetwork) {
        this.isCheckNetwork = isCheckNetwork;
    }

    public Context getContext() {
        return this.context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public boolean checkIsNetworkTypeAllowed() {
        if (this.connectivityManager == null && this.context != null) {
            this.connectivityManager = (ConnectivityManager) this.context.getSystemService("connectivity");
        }
        if (this.connectivityManager == null) {
            return true;
        }
        NetworkInfo networkInfo = this.connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null || (this.allowedNetworkTypes != -1 && (translateNetworkTypeToApiFlag(networkInfo.getType()) & this.allowedNetworkTypes) == 0)) {
            return false;
        }
        return true;
    }

    private int translateNetworkTypeToApiFlag(int networkType) {
        switch (networkType) {
            case 0:
                return 1;
            case 1:
                return 2;
            default:
                return 0;
        }
    }

    public static <K, V> PreloadDataCache<K, V> loadCache(String filePath) {
        return (PreloadDataCache) SerializeUtils.deserialization(filePath);
    }

    protected void shutdown() {
        this.threadPool.shutdown();
    }

    public List<Runnable> shutdownNow() {
        return this.threadPool.shutdownNow();
    }
}
