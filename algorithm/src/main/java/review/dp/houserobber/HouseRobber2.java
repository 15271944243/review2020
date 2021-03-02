package review.dp.houserobber;

/**
 * https://leetcode.com/problems/house-robber/ No.213 打家劫舍2
 * @author: xiaoxiaoxiang
 * @date: 2021/3/2 10:10
 */
public class HouseRobber2 {

    /**
     * You are a professional robber planning to rob houses along a street.
     * Each house has a certain amount of money stashed. All houses at this place are arranged in a circle.
     * That means the first house is the neighbor of the last one.
     * Meanwhile, adjacent houses have a security system connected,
     * and it will automatically contact the police if two adjacent houses were broken into on the same night.
     *
     * Given a list of non-negative integers nums representing the amount of money of each house,
     * return the maximum amount of money you can rob tonight without alerting the police.
     *
     * Input: nums = [2,3,2]
     * Output: 3
     * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
     * because they are adjacent houses.
     *
     * Input: nums = [0]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 1000
     */

    /**
     * 题目意思:
     * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，
     * 这意味着第一个房屋和最后一个房屋是紧挨着的。
     * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警
     *
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，能够偷窃到的最高金额。
     *
     * 此题与 HouseRobber 的区别就是, HouseRobber2 的房屋是环形,即首位相连
     *
     * 思路一: 递归求解
     *
     * 思路二: 动态规划
     * 1. 确定dp数组(dp table)以及下标的含义
     * 2. 确定递推公式
     * 3. dp数组如何初始化
     * 4. 确定遍历顺序
     * 5. 举例推导dp数组
     */
    public static void main(String[] args) {
        HouseRobber2 demo = new HouseRobber2();
        int[] nums = new int[]{2, 7, 9, 3, 1};
//        int[] nums = new int[]{1, 2, 3, 1};
        int result = demo.rob(nums);
    }

    /**
     * 此题与 HouseRobber 的区别就是首位不能相邻,所以我们就区分两个场景:
     * 0 ~ n - 1的房屋 和 1 ~ n 的房屋,递归公式和 HouseRobber 一样
     * 所以只需要在以上两种场景里去最大值就可以了
     * 1. dp[i]的定义为: 偷盗第i家的最高金额;
     * 2. 递推公式: dp[i] = max(dp[i-1], dp[i-2] + nums[i])
     * 因为不能偷相邻的房屋,[偷盗第i家的最高金额] 等于 [偷盗第i-2家的最高金额] + [偷第i家的金额]
     * 或 [偷盗第i家的最高金额] 等于 [偷盗第i-家的最高金额] （即不偷第i家）
     * 3. dp数组初始化: 0 ~ n - 1 的场景,初始化 dp[0] = nums[0] 和 dp[1] = max(nums[0], nums[1])
     * 1 ~ n 的场景,初始化 dp[0] = nums[1] 和 dp[1] = max(nums[1], nums[2])
     * 4. 遍历顺序: j = 0,1 .... n
     * 5. 举例推导dp数组:
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        // dp1 范围是 0 ~ nums.length - 2
        int[] dp1 = new int[nums.length - 1];
        dp1[0] = nums[0];
        dp1[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < dp1.length; i++) {
            dp1[i] = Math.max(dp1[i-1], dp1[i-2] + nums[i]);
        }
        // dp1 范围是 1 ~ nums.length - 1
        int[] dp2 = new int[nums.length - 1];
        dp2[0] = nums[1];
        dp2[1] = Math.max(nums[1], nums[2]);
        for (int i = 2; i < dp2.length; i++) {
            dp2[i] = Math.max(dp2[i-1], dp2[i-2] + nums[i+1]);
        }
        return Math.max(dp1[dp1.length - 1], dp2[dp2.length - 1]);
    }
}
