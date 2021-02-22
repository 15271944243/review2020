package review.dp.backpack;

/**
 * https://leetcode.com/problems/partition-equal-subset-sum/ no.416
 * 分割等和子集
 * @author: xiaoxiaoxiang
 * @date: 2021/2/22 22:13
 */
public class PartitionEqualSubsetSum {

    /**
     * Given a non-empty array nums containing only positive integers,
     * find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
     *
     * Input: nums = [1,5,11,5]
     * Output: true
     * Explanation: The array can be partitioned as [1, 5, 5] and [11].
     *
     * Input: nums = [1,2,3,5]
     * Output: false
     * Explanation: The array cannot be partitioned into equal sum subsets.
     *
     * Constraints:
     *
     * 1 <= nums.length <= 200
     * 1 <= nums[i] <= 100
     */

    /**
     * 题目意思: 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     * 1 <= nums.length <= 200
     * 1 <= nums[i] <= 100
     *
     * 思路一: 递归
     *
     * 思路二: 动态规划
     * 1. 确定dp数组(dp table)以及下标的含义
     * 2. 确定递推公式
     * 3. dp数组如何初始化
     * 4. 确定遍历顺序
     * 5. 举例推导dp数组
     */
    public static void main(String[] args) {
        PartitionEqualSubsetSum demo = new PartitionEqualSubsetSum();
        int[] nums = new int[]{1,5,11,5};
        boolean result = demo.canPartition(nums);
    }

    /**
     * 本题中我们要使用的是01背包,因为元素我们只能用一次
     * 只有确定了如下四点,才能把01背包问题套到本题上来
     *
     * 背包的体积为sum / 2
     * 背包要放入的商品（集合里的元素）重量为元素的数值,价值也为元素的数值
     * 背包如何正好装满,说明找到了总和为 sum / 2 的子集。
     * 背包中每一个元素是不可重复放入
     *
     * 1. dp[]的定义为:
     * 2. 递推公式:
     * 3. dp数组初始化:
     * 4. 遍历顺序:
     * 5.
     */
    public boolean canPartition(int[] nums) {

        return false;
    }
}
