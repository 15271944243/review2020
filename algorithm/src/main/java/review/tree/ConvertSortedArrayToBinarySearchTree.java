package review.tree;

/**
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/ No.108 将有序数组转换为二叉搜索树
 * @author: xiaoxiaoxiang
 * @date: 2021/4/25 11:40
 */
public class ConvertSortedArrayToBinarySearchTree {

    /**
     * Given an integer array nums where the elements are sorted in ascending order, convert it to
     * a height-balanced binary search tree.
     *
     * A height-balanced binary tree is a binary tree in which the depth of the two subtrees of
     * every node never differs by more than one.
     *
     * Input: nums = [-10,-3,0,5,9]
     * Output: [0,-3,9,-10,null,5]
     *
     * Input: nums = [1,3]
     * Output: [3,1]
     * Explanation: [1,3] and [3,1] are both a height-balanced BSTs.
     *
     * 1 <= nums.length <= 10^4
     * -10^4 <= nums[i] <= 10^4
     * nums is sorted in a strictly increasing order.
     */

    /**
     * 题目意思: 给你一个整数数组 nums,其中元素已经按升序排列,请你将其转换为一棵高度平衡二叉搜索树
     *
     * 高度平衡二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树
     *
     * 思路: 使用二分法找到每个节点,就是一颗高度平衡二叉搜索树
     */

    public static void main(String[] args) {
        ConvertSortedArrayToBinarySearchTree demo = new ConvertSortedArrayToBinarySearchTree();
        int[] num = new int[]{-10,-3,0,5,9,12};
        TreeNode result = demo.sortedArrayToBST(num);
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        TreeNode root = binarySearch(nums, 0, nums.length - 1);
        return root;
    }

    private TreeNode binarySearch(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (right - left)/2 + left;
        TreeNode node = new TreeNode(nums[mid]);
        TreeNode leftNode = binarySearch(nums, left, mid - 1);
        TreeNode rightNode = binarySearch(nums, mid + 1, right);
        node.left = leftNode;
        node.right = rightNode;
        return node;
    }
}
