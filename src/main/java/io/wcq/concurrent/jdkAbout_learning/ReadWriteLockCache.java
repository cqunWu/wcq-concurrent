package io.wcq.concurrent.jdkAbout_learning;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author: Chaoqun Wu
 * @description 读写锁实现缓存
 * @date: 2023/1/19 10:57
 */
public class ReadWriteLockCache<K, V> {
    // 用map存储数据
    private final Map<K, V> m = new HashMap<>();
    // 读写锁
    private final ReadWriteLock rwl = new ReentrantReadWriteLock();
    // 读锁
    private final Lock r = rwl.readLock();
    // 写锁
    private final Lock w = rwl.writeLock();
    // 读缓存
    public V get(K key) {
        r.lock();
        try {
            return m.get(key);
        } finally {
            r.unlock();
        }
    }
    // 写缓存
    public V put(K key, V value) {
        w.lock();
        try {
            return m.put(key, value);
        } finally {
            w.unlock();
        }
    }

}
