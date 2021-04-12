package review.tree;

/**
 * https://leetcode.com/problems/maximum-binary-tree/ No.654 最大二叉树
 * @author: xiaoxiaoxiang
 * @date: 2021/4/7 11:37
 */
public class MaximumBinaryTree {

    /**
     * You are given an integer array nums with no duplicates.
     * A maximum binary tree can be built recursively from nums using the following algorithm:
     *
     * 1. Create a root node whose value is the maximum value in nums.
     * 2. Recursively build the left subtree on the subarray prefix to the left of the maximum value.
     * 3. Recursively build the right subtree on the subarray suffix to the right of the maximum value.
     * Return the maximum binary tree built from nums.
     *
     * 1 <= nums.length <= 1000
     * 0 <= nums[i] <= 1000
     * All integers in nums are unique.
     */

    /**
     * 题目意思: 给定一个不含重复元素的整数数组 nums. 一个以此数组直接递归构建的[最大二叉树]定义如下:
     *
     * 1. 二叉树的根是数组 nums 中的最大元素
     * 2. 左子树是通过数组中[最大值左边部分]递归构造出的最大二叉树
     * 3. 右子树是通过数组中[最大值右边部分]递归构造出的最大二叉树
     * 返回有给定数组 nums 构建的 最大二叉树
     *
     * 输入：nums = [3,2,1,6,0,5]
     * 输出：[6,3,5,null,2,0,null,null,1]
     * 解释：递归调用如下所示：
     * - [3,2,1,6,0,5] 中的最大值是 6 ，左边部分是 [3,2,1] ，右边部分是 [0,5] 。
     *     - [3,2,1] 中的最大值是 3 ，左边部分是 [] ，右边部分是 [2,1] 。
     *         - 空数组，无子节点。
     *         - [2,1] 中的最大值是 2 ，左边部分是 [] ，右边部分是 [1] 。
     *             - 空数组，无子节点。
     *             - 只有一个元素，所以子节点是一个值为 1 的节点。
     *     - [0,5] 中的最大值是 5 ，左边部分是 [0] ，右边部分是 [] 。
     *         - 只有一个元素，所以子节点是一个值为 0 的节点。
     *         - 空数组，无子节点。
     *
     * 思路: 递归
     *
     */
    public static void main(String[] args) {
        MaximumBinaryTree demo = new MaximumBinaryTree();
        int[] nums = new int[]{3,2,1,6,0,5};
        TreeNode root = demo.constructMaximumBinaryTree(nums);
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return recursion(nums, 0, nums.length - 1);
    }

    private TreeNode recursion(int[] nums, int startIndex, int endIndex) {
        // 退出条件
        if (startIndex > endIndex) {
            return null;
        }
        int maxNumIndex = findMaxNumIndex(nums, startIndex, endIndex);
        TreeNode rootNode = new TreeNode(nums[maxNumIndex]);
        // 左子树 [startIndex, maxNumIndex - 1]
        TreeNode leftNode = recursion(nums, startIndex, maxNumIndex - 1);
        if (leftNode != null) {
            rootNode.left = leftNode;
        }
        // 右子树 [maxNumIndex + 1, endIndex]
        TreeNode rightNode = recursion(nums,maxNumIndex + 1, endIndex);
        if (rightNode != null) {
            rootNode.right = rightNode;
        }
        return rootNode;
    }

    private int findMaxNumIndex(int[] nums, int startIndex, int endIndex) {
        int max = nums[startIndex];
        int index = startIndex;
        for (int i = startIndex + 1; i <= endIndex; i++) {
            if (nums[i] > max) {
                max = nums[i];
                index = i;
            }
        }
        return index;
    }
}
