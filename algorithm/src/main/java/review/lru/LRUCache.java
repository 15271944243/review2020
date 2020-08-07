package review.lru;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/lru-cache/  No.146
 * @author: xiaoxiaoxiang
 * @date: 2020/8/5 14:52
 */
public class LRUCache {

    /**
     * Design and implement a data structure for Least Recently Used (LRU) cache.
     * It should support the following operations: get and put.
     *
     * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1
     *
     * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
     *
     * The cache is initialized with a positive capacity.
     *
     * Follow up:
     * Could you do both operations in O(1) time complexity?
     */

    /**
     * Example:
     *   LRUCache cache = new LRUCache(2);
     *   cache.put(1, 1);
     *   cache.put(2, 2);
     *   cache.get(1);       // returns 1
     *   cache.put(3, 3);    // evicts key 2
     *   cache.get(2);       // returns -1 (not found)
     *   cache.put(4, 4);    // evicts key 1
     *   cache.get(1);       // returns -1 (not found)
     *   cache.get(3);       // returns 3
     *   cache.get(4);       // returns 4
     */

    /**
     * 题目意思: 实现一个LRU Cache数据结构,要支持get和put(时间复杂度都是O(1)),初始化LRU Cache时,必须跟上一个正数的容量;
     * 思路1: 使用HashMap + 双向链表LinkList; HashMap来实现数据查询、插入都是O(1),双向链表实现查询需要被删除的节点为O(1)
     * 思路2: 直接使用LinkedHashMap,因为它就是HashMap + 双向链表的数据结构
     */

    /**
     * Your LRUCache object will be instantiated and called as such:
     * LRUCache obj = new LRUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
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


    int capacity;

    /**
     * 双向链表
     */
    LinkedList<LruCacheNode> nodeList;

    /**
     * HashMap
     */
    HashMap<Integer, LruCacheNode> nodeMap;

    public LRUCache(int capacity) {
        if (capacity < 1) {
            throw new RuntimeException("capacity must be positive");
        }
        this.capacity = capacity;
        this.nodeList = new LinkedList<>();
        this.nodeMap = new HashMap<>((int) Math.ceil(capacity / 0.75f) + 1);
    }

    /**
     * key不存在,返回-1
     * key存在,返回value,并将当前node移动到nodeList头部
     * @param key
     * @return
     */
    public int get(int key) {
        if (!nodeMap.containsKey(key)) {
            return -1;
        }
        LruCacheNode node = nodeMap.get(key);
        nodeList.remove(node);
        nodeList.addFirst(node);
        return node.getValue();
    }

    /**
     * 插入时:
     * key不存在,要往nodeMap里以及nodeList头部插入
     * key存在,更新该key对应都node,并将该node移动到nodeList头部,map里也要更新
     * 如果容量满了,要将nodeList尾部的node移除,同时在nodeMap中也移除
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        if (nodeMap.containsKey(key)) {
            LruCacheNode node = nodeMap.get(key);
            nodeList.remove(node);
            node.setValue(value);
            nodeList.addFirst(node);
            nodeMap.put(key, node);
        } else {
            LruCacheNode node = new LruCacheNode(key, value);
            if (nodeList.size() == capacity) {
                LruCacheNode lastNode = nodeList.pollLast();
                nodeMap.remove(lastNode.getKey());
            }
            nodeMap.put(key, node);
            nodeList.addFirst(node);
        }
    }

    class LruCacheNode {
        int key;

        int value;

        LruCacheNode() {
        }

        LruCacheNode(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
