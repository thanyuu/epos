package com.android.common.service;

import com.android.common.entity.CacheObject;
import java.util.Collection;
import java.util.Map.Entry;
import java.util.Set;

public interface Cache<K, V> {
    void clear();

    boolean containsKey(K k);

    Set<Entry<K, CacheObject<V>>> entrySet();

    CacheObject<V> get(K k);

    double getHitRate();

    int getSize();

    Set<K> keySet();

    CacheObject<V> put(K k, CacheObject<V> cacheObject);

    CacheObject<V> put(K k, V v);

    void putAll(Cache<K, V> cache);

    CacheObject<V> remove(K k);

    Collection<CacheObject<V>> values();
}
