package review;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/generate-parentheses/
 * @author: xiaoxiaoxiang
 * @date: 2020/6/16 23:16
 */
public class GenerateParentheses {

    /**
     *  Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
     *
     *  For example, given n = 3, a solution set is:
     *  [
     *     "((()))",
     *     "(()())",
     *     "(())()",
     *     "()(())",
     *     "()()()"
     *  ]
     */

    /**
     * 题目意思: 给定n对圆括号,编写一个函数来生成格式正确的圆括号的所有组合。
     * 什么是格式正确的圆括号组合?
     * 个人理解: 左括号与右括号数量一样;从左往右开始数,从第1个位置到第N个位置,右括号数量一定小于或等于左括号到数量
     * 思路一: 使用递归进行遍历生成
     * 思路二: 使用动态规划
     */

    public static void main(String[] args) {
        GenerateParentheses t = new GenerateParentheses();
        List<String> resultList = t.generateParenthesis(3);
    }

    /**
     * 思路一: 使用递归进行遍历生成
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> resultList = new ArrayList<>();
        String str = "";
        int leftNum = 0;
        int rightNum = 0;
        recursion(resultList, str, leftNum, rightNum, n);
        return resultList;
    }


    /**
     * 注: 这里的参数不能用StringBuffer/StringBuild#append(),因为每次递归传入的str都要是新的对象引用
     * @param list
     * @param str
     * @param leftNum
     * @param rightNum
     * @param n
     */
    private void recursion(List<String> list, String str, int leftNum, int rightNum, int n) {
        if (leftNum == n && rightNum == n) {
            list.add(str);
            return;
        }
        if (leftNum < n) {
            recursion(list, str + "(", leftNum + 1, rightNum, n);
        }
        if (leftNum > rightNum && rightNum < n) {
            recursion(list, str + ")", leftNum, rightNum + 1, n);
        }
    }

}
