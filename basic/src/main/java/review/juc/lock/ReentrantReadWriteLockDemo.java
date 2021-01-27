package review.juc.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 使用 ReentrantReadWriteLock 实现一个缓存工具
 * HashMap 用来存放缓存数据
 * @author: xiaoxiaoxiang
 * @date: 2021/1/27 14:32
 */
public class ReentrantReadWriteLockDemo<K, V> {

    /**
     * 存放数据
     */
    final Map<K, V> m = new HashMap<>();

    final ReadWriteLock rwl = new ReentrantReadWriteLock();

    final Lock r = rwl.readLock();

    final Lock w = rwl.writeLock();

    /**
     * 读缓存
     * @param key
     * @return
     */
    public V get(K key) {
        r.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " readLock lock");
            Thread.sleep(1000L);
            return m.get(key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            r.unlock();
            System.out.println(Thread.currentThread().getName() + " readLock unlock");
        }
        return null;
    }

    /**
     * 写缓存
     * @param key
     * @param value
     */
    public void set(K key, V value) {
        w.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " writeLock lock");
            Thread.sleep(1000L);
            m.put(key, value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            w.unlock();
            System.out.println(Thread.currentThread().getName() + " writeLock unlock");
        }
    }

    public static void main(String[] args) {
        ReentrantReadWriteLockDemo<String, String> demo = new ReentrantReadWriteLockDemo<>();
        Thread t1 = new Thread(() -> {
            demo.set("k1", "value1");
        }, "t1");
        Thread t2 = new Thread(() -> {
            demo.get("k1");
        }, "t2");
        Thread t3 = new Thread(() -> {
            demo.get("k1");
        }, "t3");
        t2.start();
        t1.start();
        t3.start();
    }
}
