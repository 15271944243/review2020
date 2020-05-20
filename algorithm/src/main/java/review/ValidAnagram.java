package review;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/valid-anagram/
 * @author: xiaoxiaoxiang
 * @date: 2020/5/20 23:33
 */
public class ValidAnagram {

    /**
     * Given two strings s and t , write a function to determine if t is an anagram of s.
     */

    /**
     * 题目意思: 判断是否是有效的相同字母异序词;即两个单词字母一样,但是顺序不一样,比如:
     * s = "anagram", t = "nagaram" 就是相同字母异序词
     * 思路一: 使用Map,key为字母,value为该字母出现的次数,将字符串s生成的map与字符串t生成的map比较,看是否一致,
     * 最终如果map里的value都是0,则是相同字母异序词;
     * 思路二: 在思路1的基础上,如果字符串里都是小写的字母,则一共26个字母,可以使用一个长度为26的数组来当map使用,
     * 数组里的每个元素就是的值就是这个字母出现的次数；
     * 思路三: 将两个字符串都安字典顺序排序,然后比较是否相同
     */

    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        ValidAnagram v = new ValidAnagram();
        boolean result = v.validAnagram(s, t);
        System.out.println(result);
    }

    public boolean validAnagram(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> tMap = new HashMap<>();

        for (int i=0;i<s.length();i++) {
            char c = s.charAt(i);
            if (sMap.containsKey(c)) {
                sMap.put(c, sMap.get(c) + 1);
            } else {
                sMap.put(c, 1);
            }
        }

        for (int i=0;i<t.length();i++) {
            char c = t.charAt(i);
            if (tMap.containsKey(c)) {
                tMap.put(c, tMap.get(c) + 1);
            } else {
                tMap.put(c, 1);
            }
        }

        boolean flag = true;
        for (Map.Entry<Character, Integer> entry : sMap.entrySet()) {
            Character c = entry.getKey();
            Integer i = entry.getValue();
            if (tMap.get(c) == null || !tMap.get(c).equals(i) ) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}
