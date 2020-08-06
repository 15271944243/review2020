package review.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 详见 {@link review.lru.LRUCache}
 * @author: xiaoxiaoxiang
 * @date: 2020/8/6 11:01
 */
public class LRUCache2<K, V> extends LinkedHashMap<K, V> {

    int capacity;

    public LRUCache2(int capacity) {
        // 设置一个hashmap的初始大小,最后一个true指的是让linkedhashmap按照访问顺序来进行排序
        // 最近访问的放在头，最老访问的就在尾
        super((int) Math.ceil(capacity / 0.75) + 1, 0.75f, true);
        this.capacity = capacity;
    }

    /**
     * 当map中的数据量大于指定的缓存个数的时候，就自动删除最老的数据
     * @param eldest
     * @return
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return super.size() > this.capacity;
    }

    public static void main(String[] args) {
        LRUCache2 cache = new LRUCache2(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));

    }
}
