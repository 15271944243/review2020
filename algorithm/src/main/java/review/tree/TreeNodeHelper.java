package review.tree;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/5/30 22:42
 */
public final class TreeNodeHelper {

    public static TreeNode getTreeNode() {
//         [3,2,3,null,3,null,1
        TreeNode root = new TreeNode(3);
        TreeNode left1 = new TreeNode(2);
        TreeNode right1 = new TreeNode(3);
        TreeNode right2 = new TreeNode(3);
        TreeNode right3 = new TreeNode(1);
        left1.setRight(right2);
        right1.setRight(right3);
        root.setLeft(left1);
        root.setRight(right1);

        // [4,1,null,2,null,3]
//        TreeNode root = new TreeNode(4);
//        TreeNode left1 = new TreeNode(1);
//        TreeNode left2 = new TreeNode(2);
//        TreeNode left3 = new TreeNode(3);
//        root.setLeft(left1);
//        left1.setLeft(left2);
//        left2.setLeft(left3);
        return root;
    }
}
