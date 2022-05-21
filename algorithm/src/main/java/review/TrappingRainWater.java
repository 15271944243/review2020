package review;

/**
 * https://leetcode.cn/problems/trapping-rain-water/ No.42 接雨水
 * @author: xiaoxiaoxiang
 * @date: 2022/5/19 7:30
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
//        int result = demo.trap(height);
//        int result = demo.trap2(height);
//        int result = demo.trap3(height);
        int result = demo.trap4(height);
        System.out.println(result);
    }

    /**
     * 思路1:
     * 从行的维度,自底向上,一行一行的遍历,找出每行可以接的雨水,然后汇总
     *
     * 1. 首先找出最大行高
     * 2. 开始遍历每行能接的雨水
     *
     * 超出时间限制
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if (height.length < 3) {
            return 0;
        }
        int maxHeight = getMaxHeight(height);
        int trap = 0;
        for (int i = 1; i <= maxHeight; i++) {
            int left = 0;
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

    /**
     * 思路2:
     * 从列的维度,从左到右进行计算
     *
     * 1、遍历每一列，找当前所在列左边的最大值 maxLeftHeight 和 右边的最大值 maxRightHeight
     * 2、取 min(maxLeftHeight, maxRightHeight),即木桶原理
     * 3、min(maxLeftHeight, maxRightHeight) - 当前列的高度 = 当前列可以装的水
     *
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        if (height.length < 3) {
            return 0;
        }
        int trap = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int maxLeftHeight = 0;
            for (int j = 0; j < i; j++) {
                // 找列左边的最大值 maxLeftHeight
                maxLeftHeight = Math.max(maxLeftHeight, height[j]);
            }
            int maxRightHeight = 0;
            for (int j = i+1; j < height.length; j++) {
                // 找列右边的最大值 maxRightHeight
                maxRightHeight = Math.max(maxRightHeight, height[j]);
            }

            int minHeight = Math.min(maxLeftHeight, maxRightHeight);
            if (minHeight > height[i]) {
                trap += minHeight - height[i];
            }
        }
        return trap;
    }

    /**
     * 思路3:
     * 在思路2的基础上优化：
     *
     * 1、一开始就找到最高的列 maxHeight 及它的 index
     * 2、遍历每一列时,如果当前列在最高的列的左边,那么当前列的 maxRightHeight = maxHeight
     * 3、遍历每一列时,如果当前列在最高的列的右边,那么当前列的 maxLeftHeight = maxHeight
     *
     * @param height
     * @return
     */
    public int trap3(int[] height) {
        if (height.length < 3) {
            return 0;
        }
        int maxHeight = 0;
        int maxHeightIndex = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] > maxHeight) {
                maxHeight = height[i];
                maxHeightIndex = i;
            }
        }
        int trap = 0;
        for (int i = 1; i < height.length - 1; i++) {
            if (i == maxHeightIndex) {
                continue;
            }
            int maxLeftHeight = 0;
            if (i > maxHeightIndex) {
                maxLeftHeight = maxHeight;
            } else {
                for (int j = 0; j < i; j++) {
                    // 找列左边的最大值 maxLeftHeight
                    maxLeftHeight = Math.max(maxLeftHeight, height[j]);
                }
            }
            int maxRightHeight = 0;
            if (i < maxHeightIndex) {
                maxRightHeight = maxHeight;
            } else {
                for (int j = i+1; j < height.length; j++) {
                    // 找列右边的最大值 maxRightHeight
                    maxRightHeight = Math.max(maxRightHeight, height[j]);
                }
            }
            int minHeight = Math.min(maxLeftHeight, maxRightHeight);
            if (minHeight > height[i]) {
                trap += minHeight - height[i];
            }
        }
        return trap;
    }


    /**
     * 思路4:
     * 动态规划
     * 根据思路2,我们已知
     * 前列可以装的水(trap[i]) = min(maxLeftHeight[i], maxRightHeight[i]) - 当前列的高度(height[i])
     * 通过观察题目描述中的示例图,我们可以得到
     * maxLeftHeight[i] = max(maxLeftHeight[i-1], height[i-1])
     * maxRightHeight[i] = max(maxLeftHeight[i+1], height[i+1])
     *
     * 即两个一维 dp 表达式
     * dp_left[i] = max(dp_left[i-1], height[i-1])
     * dp_right[i] = max(dp_right[i+1], height[i+1])
     *
     * @param height
     * @return
     */
    public int trap4(int[] height) {
        if (height.length < 3) {
            return 0;
        }
        int[] dp_left = new int[height.length];
        dp_left[0] = 0;
        int[] dp_right = new int[height.length];
        dp_right[height.length - 1] = 0;
        // 正向遍历,计算 dp_left
        for (int i = 1; i < height.length; i++) {
            dp_left[i] = Math.max(dp_left[i-1], height[i-1]);
        }
        // 逆向遍历,计算 dp_right
        for (int i = height.length - 2; i >=0 ; i--) {
            dp_right[i] = Math.max(dp_right[i+1], height[i+1]);
        }
        // 计算接雨水
        int trap = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int minHeight = Math.min(dp_left[i], dp_right[i]);
            if (minHeight > height[i]) {
                trap += minHeight - height[i];
            }
        }
        return trap;
    }

    /**
     * 思路5:
     * 双指针
     * 根据思路4,我们已知
     * dp_left[i] 和 dp_right[i] 数组中的元素我们其实只用一次，然后就再也不会用到了。
     * 所以我们可以不用数组，只用一个元素就行了
     *
     * 不使用 dp_left[i] 比较容易,但是 dp_right[i] 怎么处理呢?
     *
     * 看了解析,没搞懂....
     * @param height
     * @return
     */
    public int trap5(int[] height) {
        if (height.length < 3) {
            return 0;
        }
        // TODO 未完待续。。。
        int maxLeft = 0;
        int[] dp_right = new int[height.length];
        dp_right[height.length - 1] = 0;
        // 逆向遍历,计算 dp_right
        for (int i = height.length - 2; i >=0 ; i--) {
            dp_right[i] = Math.max(dp_right[i+1], height[i+1]);
        }
        // 计算接雨水
        int trap = 0;
        for (int i = 1; i < height.length - 1; i++) {
            maxLeft =  Math.max(maxLeft, height[i-1]);
            int minHeight = Math.min(maxLeft, dp_right[i]);
            if (minHeight > height[i]) {
                trap += minHeight - height[i];
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
