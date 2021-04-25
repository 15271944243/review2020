package review.tree;

/**
 * https://leetcode.com/problems/delete-node-in-a-bst/ No.450 删除二叉搜索树中的节点
 * @author: xiaoxiaoxiang
 * @date: 2021/4/25 10:44
 */
public class DeleteNodeInBST {

    /**
     * Given a root node reference of a BST and a key, delete the node with the given key in the BST.
     * Return the root node reference (possibly updated) of the BST.
     *
     * Basically, the deletion can be divided into two stages:
     *
     * 1. Search for a node to remove.
     * 2. If the node is found, delete the node.
     * Follow up: Can you solve it with time complexity O(height of tree)?
     *
     * Input: root = [5,3,6,2,4,null,7], key = 3
     * Output: [5,4,6,2,null,null,7]
     * Explanation: Given key to delete is 3. So we find the node with value 3 and delete it.
     * One valid answer is [5,4,6,2,null,null,7], shown in the above BST.
     * Please notice that another valid answer is [5,2,6,null,4,null,7] and it's also accepted.
     *
     * Input: root = [5,3,6,2,4,null,7], key = 0
     * Output: [5,3,6,2,4,null,7]
     * Explanation: The tree does not contain a node with value = 0.
     *
     * Input: root = [], key = 0
     * Output: []
     *
     * The number of nodes in the tree is in the range [0, 10^4].
     * -10^5 <= Node.val <= 10^5
     * Each node has a unique value.
     * root is a valid binary search tree.
     * -10^5 <= key <= 10^5
     */

    /**
     * 题目意思: 给定一个二叉搜索树的根节点 root 和一个值 key,删除二叉搜索树中的 key 对应的节点,并保证二叉搜索树的性质不变
     * 返回二叉搜索树（有可能被更新）的根节点的引用
     *
     * 一般来说,删除节点可分为两个步骤:
     * 1. 首先找到需要删除的节点
     * 2. 如果找到了,删除它
     * 说明: 要求算法时间复杂度为 O(h),h 为树的高度
     *
     * 思路:
     * 节点删除后,该节点的子节点需要重新组成树
     * 1. 将该节点的右子节点升为当前节点
     * 2. 将该节点的左子树加入到右子树最小的节点下
     * 3. 如果该节点的右子节点为null,则直接将该节点的左子树作为新的树
     */

    public static void main(String[] args) {
        DeleteNodeInBST demo = new DeleteNodeInBST();
        TreeNode root = demo.getTreeNode();
        int val = 3;
        TreeNode result = demo.deleteNode(root, val);
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        // 1. 搜索节点
        TreeNode cur = root;
        TreeNode parent = null;
        boolean deleteLeft = false;
        while (cur != null) {
            if (cur.val == key) {
                break;
            } else if (cur.val > key) {
                parent = cur;
                cur = cur.left;
                deleteLeft = true;
            } else {
                parent = cur;
                cur = cur.right;
                deleteLeft = false;
            }
        }
        // 没有搜索到该节点
        if (cur == null) {
            return root;
        }
        // 2. 删除节点
        if (parent == null) {
            // 删除的是根节点
            return removeRoot(cur);
        } else {
            if (cur.right == null) {
                // 右子节点为null,则直接将该节点的左子树作为新的树
                if (deleteLeft) {
                    parent.left = cur.left;
                } else {
                    parent.right = cur.left;
                }
            } else {
                TreeNode curLeft = cur.left;
                TreeNode curRight = cur.right;
                cur.left = null;
                cur.right = null;
                if (deleteLeft) {
                    parent.left = curRight;
                } else {
                    parent.right = curRight;
                }
                // 找到curRight的最小节点,将curLeft作为子节点赋给这个最小节点
                TreeNode curRightMinNode = curRight;
                while (true) {
                    if (curRightMinNode.left == null) {
                        break;
                    } else {
                        curRightMinNode = curRightMinNode.left;
                    }
                }
                curRightMinNode.left = curLeft;
            }
            return root;
        }
    }

    private TreeNode removeRoot(TreeNode cur) {
        TreeNode curLeft = cur.left;
        TreeNode curRight = cur.right;
        cur.left = null;
        cur.right = null;
        if (curRight == null) {
            // 右子节点为null,则直接将该节点的左子树作为新的树
            return curLeft;
        } else {
            // 找到curRight的最小节点,将curLeft作为子节点赋给这个最小节点
            TreeNode curRightMinNode = curRight;
            while (true) {
                if (curRightMinNode.left == null) {
                    break;
                } else {
                    curRightMinNode = curRightMinNode.left;
                }
            }
            curRightMinNode.left = curLeft;
        }
        return curRight;
    }

    private TreeNode getTreeNode() {
        // [5,3,6,2,4,null,7]
        TreeNode root = new TreeNode(5);
        TreeNode left1 = new TreeNode(3);
        TreeNode right1 = new TreeNode(6);
        TreeNode left2 = new TreeNode(2);
        TreeNode right2 = new TreeNode(4);
        TreeNode right3 = new TreeNode(7);
        root.setLeft(left1);
        root.setRight(right1);
        left1.setLeft(left2);
        left1.setRight(right2);
        right1.setRight(right3);
        return root;
    }
}
