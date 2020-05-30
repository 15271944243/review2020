package review.tree;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/5/30 22:42
 */
public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) {
        this.val = val;
    }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
