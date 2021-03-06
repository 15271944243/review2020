package review.trie;

/**
 * https://leetcode.com/problems/implement-trie-prefix-tree/
 * @author: xiaoxiaoxiang
 * @date: 2020/7/9 17:13
 */
public class Trie {

    // https://leetcode.com/problems/word-search-ii/
    /**
     * Trie树,即字典树,又称单词查找树或键树,是一种树形结构,是一种哈希树的变种.
     * 典型应用是用于统计和排序大量的字符串(但不仅限于字符串),所以经常被搜索引擎系统用于文本词频统计.
     *
     * 它的优点是: 最大限度地减少无谓的字符串比较,查找效率比哈希表高
     *
     * 它的核心思想是空间换时间,利用字符串的公共前缀来降低查询时间的开销以达到提高效率的目的
     *
     * 特征:
     * 1 根节点不包含字符,除根节点外每一个节点都只包含一个字符
     * 2 从根节点到某一节点,路径上经过的字符连接起来,为该节点对应的字符串。
     * 3 每个节点的所有子节点包含的字符都不相同。
     * 4 它的key都为字符串,能做到高效查询和插入,时间复杂度为O(k),k为字符串长度,缺点是如果大量字符串没有共同前缀时很耗内存
     */

    /**
     * Implement a trie with insert, search, and startsWith methods.
     *
     * Example:
     *
     * Trie trie = new Trie();
     *
     * trie.insert("apple");
     * trie.search("apple");   // returns true
     * trie.search("app");     // returns false
     * trie.startsWith("app"); // returns true
     * trie.insert("app");
     * trie.search("app");     // returns true
     *
     * Note:
     * You may assume that all inputs are consist of lowercase letters a-z.
     * All inputs are guaranteed to be non-empty strings.
     */

    /**
     * 题目意思: 实现一个Trie树,即字典树,并有insert、search、startsWith方法
     * 所有的输入都是小写字母a-z,所有输入都是非空字符串
     */

    /**
     * Your Trie object will be instantiated and called as such:
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */

    /** Initialize your data structure here. */
    public Trie() {

    }

    /**
     * 节点值(这个场景,val没啥用,根据index就可以计算出val)
     */
//    Character val;
    /**
     * 因为只有26个a-z的小写字母,正好可以转化为0-25的索引(如果没有这个限制,就只能用Map存了)
     */
    Trie[] subList = new Trie[26];
    /**
     * 是否是叶子节点
     */
    boolean isEnd;

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Trie trie = this;
        for (int i =0; i< word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (index > 25) {
                throw new ArrayIndexOutOfBoundsException(index);
            }
            Trie sub = trie.subList[index];
            if (sub == null) {
                sub = new Trie();
//                sub.val = c;
                trie.subList[index] = sub;
            }
            trie = sub;
        }
        trie.isEnd = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie trie = this;
        for (int i =0; i< word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (index > 25) {
                throw new ArrayIndexOutOfBoundsException(index);
            }
            Trie sub = trie.subList[index];
            if (sub == null) {
                return false;
            }
            trie = sub;
        }
        return trie.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie trie = this;
        for (int i =0; i< prefix.length(); i++) {
            char c = prefix.charAt(i);
            int index = c - 'a';
            if (index > 25) {
                throw new ArrayIndexOutOfBoundsException(index);
            }
            Trie sub = trie.subList[index];
            if (sub == null) {
                return false;
            }
            trie = sub;
        }
        return true;
    }

    public static void main(String[] args) {
//        Trie obj = new Trie();
//        String word = "fsdzceqwrwa";
//        obj.insert(word);
//        System.out.println(obj.search(word));
//        System.out.println(obj.search("dsdsqwe"));
//        System.out.println(obj.startsWith("fsdzc"));
//        System.out.println(obj.startsWith("asfq"));

        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));  // returns true
        System.out.println(trie.search("app")); // returns false

        System.out.println(trie.startsWith("app")); // returns true
        trie.insert("app");
        System.out.println(trie.search("app"));     // returns true
    }
}
