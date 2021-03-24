package review.lfu;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/lfu-cache/  No.460
 * @author: xiaoxiaoxiang
 * @date: 2020/8/5 14:53
 */
public class LFUCache {

    /**
     * Design and implement a data structure for Least Frequently Used (LFU) cache.
     * It should support the following operations: get and put.
     * get(key) - Get the value (will always be positive) of the key if the key exists in the cache,
     * otherwise return -1.
     * put(key, value) - Set or insert the value if the key is not already present.
     * When the cache reaches its capacity, it should invalidate the least frequently used item
     * before inserting a new item. For the purpose of this problem,
     * when there is a tie (i.e., two or more keys that have the same frequency),
     * the least recently used key would be evicted.
     *
     * Note that the number of times an item is used is the number of calls to the get and
     * put functions for that item since it was inserted. This number is set to zero
     * when the item is removed.
     *
     * Follow up:
     * Could you do both operations in O(1) time complexity?
     *
     * Example:
     *
     *   LFUCache cache = new LFUCache(2);
     *   cache.put(1, 1);
     *   cache.put(2, 2);
     *   cache.get(1);       // returns 1
     *   cache.put(3, 3);    // evicts key 2
     *   cache.get(2);       // returns -1 (not found)
     *   cache.get(3);       // returns 3.
     *   cache.put(4, 4);    // evicts key 1.
     *   cache.get(1);       // returns -1 (not found)
     *   cache.get(3);       // returns 3
     *   cache.get(4);       // returns 4
     */


    /**
     * 题目意思: 实现一个LFU Cache数据结构,要支持get和put(时间复杂度都是O(1))操作,
     * 初始化LFU Cache时,必须跟上一个正数的容量;
     * 如果cache容量已满,则将最少使用的key-value对移除,如果存在多个key-value对的使用次数相同,
     * 则将上次访问时间最早的key-value对移除
     *
     * 思路1: 使用HashMap + TreeMap(换成双向链表也行) + 双向链表DoubleLinkList;
     * HashMap来实现数据查询、插入和删除都是O(1),
     * TreeMap实现访问次数的排序,它的value就是DoubleLinkList
     * 双向链表DoubleLinkList实现访问时间的顺序
     *
     * 思路2:
     */

    /**
     * Your LFUCache object will be instantiated and called as such:
     * LFUCache obj = new LFUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3.
        cache.put(4, 4);    // evicts key 1.
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3
        System.out.println(cache.get(4));       // returns 4
    }

    public LFUCache(int capacity) {
        if (capacity < 0) {
            throw new RuntimeException("capacity must be positive");
        }
        this.capacity = capacity;
        this.usedCountMap = new TreeMap<>();
        this.nodeMap = new HashMap<>((int) Math.ceil(capacity / 0.75f) + 1);
    }

    /**
     * key不存在,返回-1
     * key存在,返回value,并将当前node的访问次数+1,然后整理顺序,包括访问顺序与同一访问次数下的访问时间顺序
     * @param key
     * @return
     */
    public int get(int key) {
        int val = -1;
        if (nodeMap.containsKey(key)) {

            LFUCacheNode node = nodeMap.get(key);
            val = node.value;

            int frequency = node.frequency;
            // 在旧的LinkedList中删除当前node
            LinkedList<LFUCacheNode> linkedList = usedCountMap.get(frequency);
            linkedList.remove(node);
            if (linkedList.isEmpty()) {
                usedCountMap.remove(frequency);
            }
            // 当前node访问次数+1
            frequency++;
            node.frequency = frequency;
            // 将node放入访问次数+1后的LinkedList中,LinkedList按访问时间排序,访问时间晚的放在头部
            linkedList = usedCountMap.get(frequency) == null ? new LinkedList<>() : usedCountMap.get(frequency);
            usedCountMap.put(node.frequency, linkedList);
            linkedList.addFirst(node);
        }

        return val;
    }

    /**
     * nodeMap包含key,相当于get,只不过需要更新node的value
     * nodeMap不包含key,判断是否超过容量,如果超过,则删除key
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (nodeMap.containsKey(key)) {
            // 可以当作get处理,只不过需要更新node的value
            LFUCacheNode node = nodeMap.get(key);
            node.value = value;
            int frequency = node.frequency;
            // 在旧的LinkedList中删除当前node
            LinkedList<LFUCacheNode> linkedList = usedCountMap.get(frequency);
            linkedList.remove(node);
            if (linkedList.isEmpty()) {
                usedCountMap.remove(frequency);
            }
            // 当前node访问次数+1
            frequency++;
            node.frequency = frequency;
            // 将node放入访问次数+1后的LinkedList中,LinkedList按访问时间排序,访问时间晚的放在头部
            linkedList = usedCountMap.get(frequency) == null ? new LinkedList<>() : usedCountMap.get(frequency);
            linkedList.addFirst(node);
            usedCountMap.put(frequency, linkedList);
        } else {
            if (nodeMap.size() == this.capacity) {
                // 删除访问次数最少的node,如果有多个,则删除访问时间最久的
                Integer firstEntryKey = usedCountMap.firstEntry().getKey();
                LinkedList<LFUCacheNode> linkedList = usedCountMap.firstEntry().getValue();
                LFUCacheNode lastNode = linkedList.pollLast();
                if (linkedList.isEmpty()) {
                    usedCountMap.remove(firstEntryKey);
                }
                nodeMap.remove(lastNode.key);
            }
            // 插入新的node
            LFUCacheNode node = new LFUCacheNode(key, value, 1);
            LinkedList<LFUCacheNode> linkedList = usedCountMap.get(node.frequency) == null ?
                    new LinkedList<>() : usedCountMap.get(node.frequency);
            linkedList.addFirst(node);
            usedCountMap.put(node.frequency, linkedList);
            nodeMap.put(key, node);
        }
    }

    int capacity;

    /**
     * TreeMap key:访问次数 value: 访问时间链表
     */
    TreeMap<Integer, LinkedList<LFUCacheNode>> usedCountMap;

    /**
     * HashMap
     */
    HashMap<Integer, LFUCacheNode> nodeMap;

    class LFUCacheNode {
        int frequency;

        int key;

        int value;

        public LFUCacheNode() {
        }

        public LFUCacheNode(int key, int value, int frequency) {
            this.key = key;
            this.value = value;
            this.frequency = frequency;
        }
    }
}
