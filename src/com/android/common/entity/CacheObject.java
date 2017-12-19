package com.android.common.entity;

import com.android.common.utils.ObjectUtils;
import java.io.Serializable;

public class CacheObject<V> implements Serializable, Comparable<CacheObject<V>> {
    private static final long serialVersionUID = 1;
    protected V data;
    protected long enterTime;
    protected boolean isExpired;
    protected boolean isForever;
    protected long lastUsedTime;
    protected int priority;
    protected long usedCount;

    public CacheObject() {
        this.enterTime = System.currentTimeMillis();
        this.lastUsedTime = System.currentTimeMillis();
        this.usedCount = 0;
        this.priority = 0;
        this.isExpired = false;
        this.isForever = false;
    }

    public CacheObject(V data) {
        this();
        this.data = data;
    }

    public long getEnterTime() {
        return this.enterTime;
    }

    public void setEnterTime(long enterTime) {
        this.enterTime = enterTime;
    }

    public long getLastUsedTime() {
        return this.lastUsedTime;
    }

    public void setLastUsedTime(long lastUsedTime) {
        this.lastUsedTime = lastUsedTime;
    }

    public long getUsedCount() {
        return this.usedCount;
    }

    public void setUsedCount(long usedCount) {
        this.usedCount = usedCount;
    }

    public synchronized long getAndIncrementUsedCount() {
        long j;
        j = this.usedCount;
        this.usedCount = 1 + j;
        return j;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isExpired() {
        return this.isExpired;
    }

    public void setExpired(boolean isExpired) {
        this.isExpired = isExpired;
    }

    public boolean isForever() {
        return this.isForever;
    }

    public void setForever(boolean isForever) {
        this.isForever = isForever;
    }

    public V getData() {
        return this.data;
    }

    public void setData(V data) {
        this.data = data;
    }

    public int compareTo(CacheObject<V> o) {
        return o == null ? 1 : ObjectUtils.compare(this.data, o.data);
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        CacheObject<V> obj = (CacheObject) o;
        if (ObjectUtils.isEquals(this.data, obj.data) && this.enterTime == obj.enterTime && this.priority == obj.priority && this.isExpired == obj.isExpired && this.isForever == obj.isForever) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.data == null ? 0 : this.data.hashCode();
    }
}
