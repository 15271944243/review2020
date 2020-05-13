package review;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * https://leetcode.com/problems/valid-parentheses/
 * @author: xiaoxiaoxiang
 * @date: 2020/5/12 23:31
 */
public class ValidParentheses {

    /**
     * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
     *
     * An input string is valid if:
     *
     * Open brackets must be closed by the same type of brackets.
     * Open brackets must be closed in the correct order.
     * Note that an empty string is also considered valid.
     */

    /**
     * 使用栈,如果是左括号,push进去;
     * 如果是右括号,查找栈顶是否为匹配的左括号,有则pop,没有则返回false
     * 字符串的字符遍历完成后,如果栈为空,则完全匹配返回true,反之返回false
     */
    public boolean isValid(String s) {
        if (s == null || s.trim().length() == 0) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> charMap = new HashMap<>();
        charMap.put(')', '(');
        charMap.put('}', '{');
        charMap.put(']', '[');
        for (int i=0;i<s.length();i++) {
            Character c = s.charAt(i);
            if (charMap.containsKey(c)) {
                Character top = stack.empty() ? '#' : stack.peek();
                if (top.equals(charMap.get(c))) {
                    stack.pop();
                } else {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        return stack.empty();
    }

    public static void main(String[] args) {
        ValidParentheses validParentheses = new ValidParentheses();
        System.out.println(validParentheses.isValid("]"));
    }
}
