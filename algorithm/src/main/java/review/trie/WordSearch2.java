package review.trie;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/word-search-ii/
 * @author: xiaoxiaoxiang
 * @date: 2020/7/16 10:18
 */
public class WordSearch2 {
    /**
     * Given a 2D board and a list of words from the dictionary, find all words in the board.
     *
     * Each word must be constructed from letters of sequentially adjacent cell,
     * where "adjacent" cells are those horizontally or vertically neighboring.
     * The same letter cell may not be used more than once in a word.
     *
     * Example:
     *
     *  Input:
     *  board = [
     *      ['o','a','a','n'],
     *      ['e','t','a','e'],
     *      ['i','h','k','r'],
     *      ['i','f','l','v']
     *  ]
     *
     *  words = ["oath","pea","eat","rain"]
     *
     *  Output: ["eat","oath"]
     *
     *  Note:
     *
     *  All inputs are consist of lowercase letters a-z.
     *  The values of words are distinct.
     */

    /**
     * 题目意思:
     * 给一个2D的棋盘(即二维数组board)和一个words数组,在board里找出words数组里所有符合条件的单词;
     * board里找出的单词必须有相邻的字母组成,这里的`相邻`的意思就是水平垂直方向相邻(上下左右相邻),每个字母只能使用一次
     *
     * 所有输入(board及words)都是小写字母a-z;
     * words里的值不重复;
     */


    /**
     * 思路:
     * DFS + Trie树
     * 把wrods里的单词放入Trie树中,DFS遍历board中的字母,使用Trie树进行匹配,如何能匹配上,则返回结果;
     */

    public static void main(String[] args) {
        /*char[][] board = new char[][]{
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}
        };*/
        char[][] board = new char[][]{
                {'a', 'a'}
        };
//        String[] words = new String[]{"oath", "oat","pea","eat","rain"};
        String[] words = new String[]{"aaa"};
        WordSearch2 wordSearch2 = new WordSearch2();
        List<String> result = wordSearch2.findWords(board, words);
        System.out.println(111);
    }


    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        if (board.length == 0 || words.length == 0) {
            return result;
        }
        boolean[][] visit = new boolean[board.length][board[0].length];
        Trie trie = initTrie(words);
        for (int i=0;i<board.length;i++) {
            for (int j=0;j<board[i].length;j++) {
                String word = String.valueOf(board[i][j]);
                if (trie.startsWith(word)) {
                    dfs(board, trie, word, i, j, visit, result);
                }
            }
        }
        return result;
    }

    private Trie initTrie(String[] words) {
        Trie trie = new Trie();
        for (int i=0;i < words.length;i++) {
            trie.insert(words[i]);
        }
        return trie;
    }

    private void dfs(char[][] board, Trie trie, String word, int i, int j, boolean[][] visit, List<String> result) {
        // 不能走回头路
        if (visit[i][j]) {
            return;
        }
        visit[i][j] = true;

        if (trie.search(word) && !result.contains(word)) {
            result.add(word);
        }

        if (i - 1 > -1) {
            // 上相邻
            String newWord = word + board[i - 1][j];
            // 继续搜索,可能会出现多个,比如app与apple
            if (trie.startsWith(newWord)) {
                dfs(board, trie, newWord, i - 1, j, visit, result);
            }
        }
        if (i + 1 < board.length) {
            // 下相邻
            String newWord = word + board[i + 1][j];
            if (trie.startsWith(newWord)) {
                dfs(board, trie, newWord, i + 1, j, visit, result);
            }
        }
        if (j - 1 > -1) {
            // 左相邻
            String newWord = word + board[i][j - 1];
            if (trie.startsWith(newWord)) {
                dfs(board, trie, newWord, i, j - 1, visit, result);
            }
        }
        if (j + 1 < board[i].length) {
            // 右相邻
            String newWord = word + board[i][j + 1];
            if (trie.startsWith(newWord)) {
                dfs(board, trie, newWord, i, j + 1, visit, result);
            }
        }

        visit[i][j] = false;
    }
}
