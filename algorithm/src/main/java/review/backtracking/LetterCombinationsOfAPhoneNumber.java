package review.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/ No.17
 * 电话号码的字母组合
 * @author: xiaoxiaoxiang
 * @date: 2021/1/26 17:15
 */
public class LetterCombinationsOfAPhoneNumber {

    /**
     * Given a string containing digits from 2-9 inclusive,
     * return all possible letter combinations that the number could represent.
     * Return the answer in any order.
     *
     * A mapping of digit to letters (just like on the telephone buttons) is given below.
     * Note that 1 does not map to any letters.
     *
     *
     * Example 1:
     *
     * Input: digits = "23"
     * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
     * Example 2:
     *
     * Input: digits = ""
     * Output: []
     * Example 3:
     *
     * Input: digits = "2"
     * Output: ["a","b","c"]
     *
     *
     * Constraints:
     *
     * 0 <= digits.length <= 4
     * digits[i] is a digit in the range ['2', '9'].
     */

    /**
     * 题目意思: 给定一个仅包含数字 2-9 的字符串,返回所有它能表示的字母组合
     * 约束条件:
     * 给出数字到字母的映射如下(与电话按键相同).注意 1 不对应任何字母
     * 0 <= digits.length <= 4
     *
     * 思路: 采用递归-回溯-剪枝的思想
     */
    public static void main(String[] args) {
        LetterCombinationsOfAPhoneNumber demo = new LetterCombinationsOfAPhoneNumber();

        List<String> result = new ArrayList<>();
        result = demo.letterCombinations("");
        result = demo.letterCombinations("23");
    }

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || "".equals(digits)) {
            return result;
        }
        char[] chars = digits.toCharArray();
        backtracking(chars, 0, "", result);
        return result;
    }

    /**
     *
     * @param digits
     * @param index      当前digits数组元素
     * @param tmpResult
     * @param result
     */
    private void backtracking(char[] digits, int index, String tmpResult, List<String> result) {
        if (index == digits.length) {
            result.add(tmpResult);
            return;
        }
        char c = digits[index];
        char[] chars = getLetters(c);
        for (int j = 0; j < chars.length; j++) {
            char letter = chars[j];
            backtracking(digits, index + 1, tmpResult + letter, result);
        }
    }

    private static final char[] NUM_TWO_LETTERS = new char[]{'a', 'b', 'c'};
    private static final char[] NUM_THREE_LETTERS = new char[]{'d', 'e', 'f'};
    private static final char[] NUM_FOUR_LETTERS = new char[]{'g', 'h', 'i'};
    private static final char[] NUM_FIVE_LETTERS = new char[]{'j', 'k', 'l'};
    private static final char[] NUM_SIX_LETTERS = new char[]{'m', 'n', 'o'};
    private static final char[] NUM_SEVEN_LETTERS = new char[]{'p', 'q', 'r', 's'};
    private static final char[] NUM_EIGHT_LETTERS = new char[]{'t', 'u', 'v'};
    private static final char[] NUM_NINE_LETTERS = new char[]{'w', 'x', 'y', 'z'};

    private char[] getLetters(char c) {
        char[] chars = null;
        switch (c) {
            case '2':
                chars = NUM_TWO_LETTERS;
                break;
            case '3':
                chars = NUM_THREE_LETTERS;
                break;
            case '4':
                chars = NUM_FOUR_LETTERS;
                break;
            case '5':
                chars = NUM_FIVE_LETTERS;
                break;
            case '6':
                chars = NUM_SIX_LETTERS;
                break;
            case '7':
                chars = NUM_SEVEN_LETTERS;
                break;
            case '8':
                chars = NUM_EIGHT_LETTERS;
                break;
            case '9':
                chars = NUM_NINE_LETTERS;
                break;
            default:
                break;
        }
        return chars;
    }
}
