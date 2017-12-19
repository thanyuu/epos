package com.android.common.service.impl;

import com.android.common.entity.CacheObject;
import com.android.common.service.Cache;
import com.android.common.service.CacheFullRemoveType;
import com.android.common.utils.MapUtils;
import com.android.common.utils.SerializeUtils;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class SimpleCache<K, V> implements Cache<K, V>, Serializable {
    public static final int DEFAULT_MAX_SIZE = 64;
    private static final long serialVersionUID = 1;
    protected Map<K, CacheObject<V>> cache;
    private CacheFullRemoveType<V> cacheFullRemoveType;
    protected AtomicLong hitCount;
    private final int maxSize;
    protected AtomicLong missCount;
    private long validTime;

    public SimpleCache() {
        this(64);
    }

    public SimpleCache(int maxSize) {
        this.hitCount = new AtomicLong(0);
        this.missCount = new AtomicLong(0);
        if (maxSize <= 0) {
            throw new IllegalArgumentException("The maxSize of cache must be greater than 0.");
        }
        this.maxSize = maxSize;
        this.cacheFullRemoveType = new RemoveTypeEnterTimeFirst();
        this.validTime = -1;
        this.cache = new ConcurrentHashMap(maxSize);
    }

    public int getMaxSize() {
        return this.maxSize;
    }

    public long getValidTime() {
        return this.validTime;
    }

    public void setValidTime(long validTime) {
        if (validTime <= 0) {
            validTime = -1;
        }
        this.validTime = validTime;
    }

    public CacheFullRemoveType<V> getCacheFullRemoveType() {
        return this.cacheFullRemoveType;
    }

    public void setCacheFullRemoveType(CacheFullRemoveType<V> cacheFullRemoveType) {
        if (cacheFullRemoveType == null) {
            throw new IllegalArgumentException("The cacheFullRemoveType of cache cannot be null.");
        }
        this.cacheFullRemoveType = cacheFullRemoveType;
    }

    public int getSize() {
        removeExpired();
        return this.cache.size();
    }

    public CacheObject<V> get(K key) {
        CacheObject obj = (CacheObject) this.cache.get(key);
        if (isExpired(obj) || obj == null) {
            this.missCount.incrementAndGet();
            return null;
        }
        this.hitCount.incrementAndGet();
        setUsedInfo(obj);
        return obj;
    }

    protected synchronized void setUsedInfo(CacheObject<V> obj) {
        if (obj != null) {
            obj.getAndIncrementUsedCount();
            obj.setLastUsedTime(System.currentTimeMillis());
        }
    }

    public CacheObject<V> put(K key, V value) {
        CacheObject obj = new CacheObject();
        obj.setData(value);
        obj.setForever(this.validTime == -1);
        return put((Object) key, obj);
    }

    public synchronized CacheObject<V> put(K key, CacheObject<V> value) {
        if (this.cache.size() >= this.maxSize && removeExpired() <= 0) {
            if (this.cacheFullRemoveType instanceof RemoveTypeNotRemove) {
                value = null;
            } else if (fullRemoveOne() == null) {
                value = null;
            }
        }
        value.setEnterTime(System.currentTimeMillis());
        this.cache.put(key, value);
        return value;
    }

    public void putAll(Cache<K, V> cache2) {
        for (Entry<K, CacheObject<V>> e : cache2.entrySet()) {
            if (e != null) {
                put(e.getKey(), (CacheObject) e.getValue());
            }
        }
    }

    public boolean containsKey(K key) {
        return this.cache.containsKey(key) && !isExpired((Object) key);
    }

    protected boolean isExpired(K key) {
        return this.validTime == -1 ? false : isExpired((CacheObject) this.cache.get(key));
    }

    public CacheObject<V> remove(K key) {
        return (CacheObject) this.cache.remove(key);
    }

    protected CacheObject<V> fullRemoveOne() {
        if (MapUtils.isEmpty(this.cache) || (this.cacheFullRemoveType instanceof RemoveTypeNotRemove)) {
            return null;
        }
        Object keyToRemove = null;
        CacheObject<V> valueToRemove = null;
        for (Entry<K, CacheObject<V>> entry : this.cache.entrySet()) {
            if (entry != null) {
                if (valueToRemove == null) {
                    valueToRemove = (CacheObject) entry.getValue();
                    keyToRemove = entry.getKey();
                } else if (this.cacheFullRemoveType.compare((CacheObject) entry.getValue(), valueToRemove) < 0) {
                    valueToRemove = (CacheObject) entry.getValue();
                    keyToRemove = entry.getKey();
                }
            }
        }
        if (keyToRemove == null) {
            return valueToRemove;
        }
        this.cache.remove(keyToRemove);
        return valueToRemove;
    }

    protected synchronized int removeExpired() {
        int i;
        if (this.validTime == -1) {
            i = 0;
        } else {
            i = 0;
            for (Entry<K, CacheObject<V>> entry : this.cache.entrySet()) {
                if (entry != null && isExpired((CacheObject) entry.getValue())) {
                    this.cache.remove(entry.getKey());
                    i++;
                }
            }
        }
        return i;
    }

    public void clear() {
        this.cache.clear();
    }

    protected boolean isExpired(CacheObject<V> obj) {
        return this.validTime != -1 && (obj == null || ((obj.isExpired() && !obj.isForever()) || obj.getEnterTime() + this.validTime < System.currentTimeMillis()));
    }

    public long getHitCount() {
        return this.hitCount.get();
    }

    public long getMissCount() {
        return this.missCount.get();
    }

    public synchronized double getHitRate() {
        long total;
        total = this.hitCount.get() + this.missCount.get();
        return total == 0 ? 0.0d : ((double) this.hitCount.get()) / ((double) total);
    }

    public Set<K> keySet() {
        removeExpired();
        return this.cache.keySet();
    }

    public Set<Entry<K, CacheObject<V>>> entrySet() {
        removeExpired();
        return this.cache.entrySet();
    }

    public Collection<CacheObject<V>> values() {
        removeExpired();
        return this.cache.values();
    }

    public static <K, V> SimpleCache<K, V> loadCache(String filePath) {
        return (SimpleCache) SerializeUtils.deserialization(filePath);
    }

    public static <K, V> void saveCache(String filePath, SimpleCache<K, V> cache) {
        SerializeUtils.serialization(filePath, cache);
    }
}
