package review;

/**
 * https://leetcode.cn/problems/trapping-rain-water/ No.42 接雨水
 * @author: xiaoxiaoxiang
 * @date: 2022/5/20 7:30
 */
public class TrappingRainWater {

    /**
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     *
     * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
     * 输出：6
     * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
     *
     * 提示：
     * n == height.length
     * 1 <= n <= 2 * 104
     * 0 <= height[i] <= 105
     */

    public static void main(String[] args) {
        TrappingRainWater demo = new TrappingRainWater();
        int[] height = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        int result = demo.trap(height);
        System.out.println(result);
    }

    /**
     * 思路:
     * 从行的维度,自底向上,一行一行的遍历,找出每行可以接的雨水,然后汇总
     *
     * 1. 首先找出最大行高
     * 2. 开始遍历每行能接的雨水
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int maxHeight = getMaxHeight(height);
        int trap = 0;
        for (int i = 1; i <= maxHeight; i++) {
            int left = 0;
            int right = 0;
            int r = 0;
            for (int j = 0; j < height.length; j++) {
                if (left == 0 && height[j] < i) {
                    continue;
                }
                if (left == 0) {
                    // 找出左边(左边和右边形成一个接雨水的坑)
                    left = i;
                    continue;
                }
                // 找到了右边,将右边试做左边
                if (height[j] >= i) {
                    trap += r;
                    r = 0;
                    left = i;
                    continue;
                }
                // height[j] < i,有空位可以填充雨水,因为是按层级循环,所以这里 +1 即可
                r += 1;
            }
        }
        return trap;
    }


    private int getMaxHeight(int[] height) {
        int maxHeight = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] > maxHeight) {
                maxHeight = height[i];
            }
        }
        return maxHeight;
    }
}
