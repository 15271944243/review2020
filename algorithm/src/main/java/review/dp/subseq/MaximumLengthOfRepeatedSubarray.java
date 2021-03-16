package review.dp.subseq;

/**
 * https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray/ No.718 最长重复子数组
 * @author: xiaoxiaoxiang
 * @date: 2021/3/16 10:38
 */
public class MaximumLengthOfRepeatedSubarray {

    /**
     * Given two integer arrays A and B,
     * return the maximum length of an subarray that appears in both arrays.
     *
     * Input:
     * A: [1,2,3,2,1]
     * B: [3,2,1,4,7]
     * Output: 3
     * Explanation:
     * The repeated subarray with maximum length is [3, 2, 1].
     *
     * Note:
     * 1 <= len(A), len(B) <= 1000
     * 0 <= A[i], B[i] < 100
     */

    /**
     *  题目意思: 给两个整数数组 A 和 B,返回两个数组中公共的、长度最长的子数组的长度
     *
     *  思路: 动态规划
     *  1. 确定dp数组(dp table)以及下标的含义
     *  2. 确定递推公式
     *  3. dp数组如何初始化
     *  4. 确定遍历顺序
     *  5. 举例推导dp数组
     */
    public static void main(String[] args) {
        MaximumLengthOfRepeatedSubarray demo = new MaximumLengthOfRepeatedSubarray();
        int[] numsA = new int[]{1,2,3,2,1};
        int[] numsB = new int[]{3,2,1,4,7};
        int result = demo.findLength(numsA, numsB);
        System.out.println(result);
    }

    /**
     * DP思路二
     * 1. dp[i]的定义为:
     *
     *
     * 2. 递推公式:

     * 最后结果就是 max(dp[0], dp[1] ... dp[n])
     * 3. dp数组初始化: dp[0] = 1
     * 4. 遍历顺序: 正序遍历
     * 5. 举例推导dp数组:
     * @param A
     * @param B
     * @return
     */
    public int findLength(int[] A, int[] B) {

        return 0;
    }
}
