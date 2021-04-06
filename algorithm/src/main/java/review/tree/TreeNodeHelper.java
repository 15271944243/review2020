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

    public static TreeNode getTreeNode2() {
//      [3,9,20,null,null,15,7]
        TreeNode root = new TreeNode(3);
        TreeNode left1 = new TreeNode(9);
        TreeNode right1 = new TreeNode(20);
        TreeNode left3 = new TreeNode(15);
        TreeNode right3 = new TreeNode(7);
        right1.setRight(right3);
        right1.setLeft(left3);
        root.setLeft(left1);
        root.setRight(right1);
        return root;
    }

    public static TreeNode getTreeNode3() {
//        [1,2,2,null,3,null,3]
        TreeNode root = new TreeNode(1);
        TreeNode left1 = new TreeNode(2);
        TreeNode right1 = new TreeNode(2);
        TreeNode right2 = new TreeNode(3);
        TreeNode left3 = new TreeNode(3);
        root.setLeft(left1);
        root.setRight(right1);
        left1.setRight(right2);
        right1.setLeft(left3);
        return root;
    }

    public static TreeNode getTreeNode4() {
//        [1,2,2,null,3,null,3]
        TreeNode root = new TreeNode(1);
        TreeNode left1 = new TreeNode(2);
        TreeNode right1 = new TreeNode(3);
        TreeNode left2 = new TreeNode(4);
        TreeNode right2 = new TreeNode(5);
        TreeNode left3 = new TreeNode(6);
        root.setLeft(left1);
        root.setRight(right1);
        left1.setLeft(left2);
        left1.setRight(right2);
        right1.setLeft(left3);
        return root;
    }
}
