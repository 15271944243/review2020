package review.tree;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 * No.106 从中序与后序遍历序列构造二叉树
 * @author: xiaoxiaoxiang
 * @date: 2021/4/7 11:31
 */
public class ConstructBinaryTree {

    /**
     * Given two integer arrays inorder and postorder where inorder is the inorder traversal
     * of a binary tree and postorder is the postorder traversal of the same tree,
     * construct and return the binary tree.
     *
     *
     * Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
     * Output: [3,9,20,null,null,15,7]
     *
     * Input: inorder = [-1], postorder = [-1]
     * Output: [-1]
     *
     * 1 <= inorder.length <= 3000
     * postorder.length == inorder.length
     * -3000 <= inorder[i], postorder[i] <= 3000
     * inorder and postorder consist of unique values.
     * Each value of postorder also appears in inorder.
     * inorder is guaranteed to be the inorder traversal of the tree.
     * postorder is guaranteed to be the postorder traversal of the tree.
     */

    /**
     * 思考:
     * - 前序和中序可以唯一确定一颗二叉树
     * - 后序和中序可以唯一确定一颗二叉树
     *
     * 那么前序和后序可不可以唯一确定一颗二叉树呢?
     * 不能,因为没有中序遍历无法确定左右部分,也就是无法分割
     *
     * https://mp.weixin.qq.com/s/7r66ap2s-shvVvlZxo59xg
     */

    /**
     * 题目意思: 根据一棵树的中序遍历与后序遍历构造二叉树
     *
     * 思路:
     * InOrder 数组: 第一个元素(下标为0)是最左边的叶子节点,最后一个元素是最右边的叶子节点
     * PostOrder 数组: 第一个元素(下标为0)是最左边的叶子节点,最后一个元素是最根节点
     *
     * 1. 首先我们可以从 PostOrder 数组获取到根节点
     * 2. 根据 InOrder 数组的特性,根节点的左子树,在根节点的左边;右子树在根节点的右边;
     * 所以,假设 InOrder 数组长度为 n,根节点下标为 i,[i+1, n-1] 就是根节点的右子树,节点数量为 n-i-1 个,
     * [0, i-1]就是根节点的左子树,节点数量为 i 个
     * 3. 根据 PostOrder 数组的特性(LRD顺序),它最后一个元素是最根节点,从后往前数,顺序应该是: 根节点 -> 右子树 -> 左子树;
     * 结合第2点,得出: [0, i-1] 是根节点的左子树,[i, n-2] 是根节点的右子树
     * 4. 根据2、3点,得出: PostOrder 数组的[0, i-1] 与 InOrder 数组的[0, i-1] 匹配,都是根节点的左子树
     * PostOrder 数组的[i, n-1] 与 InOrder 数组的[i+1, n-1] 匹配,都是根节点的右子树
     * 5. 既然已经匹配上了根节点的左右子树,可以把跟节点的左右子树分别当作一棵树,假设就是 TreeLeft 和 TreeRight 把,
     * 同理,我们也能找到 TreeLeft 和 TreeRight 的根节点,不断递归,就能找到所有节点,并组成二叉树
     */
    public static void main(String[] args) {
        ConstructBinaryTree demo = new ConstructBinaryTree();
        int[] inorder = new int[]{9,3,15,20,7};
        int[] postorder = new int[]{9,15,7,20,3};
        TreeNode root = demo.buildTree(inorder, postorder);
    }

    /**
     * 以 inorder = [9,3,15,20,7], postorder = [9,15,7,20,3] 为例
     *      3
     *   9     20
     *       15   7
     * 1. postorder 数组,根节点为3
     * 2. inorder 数组,根节点为3, [15,20,7] 是右子树,[9] 是左子树
     * 3. postorder 数组, [15,7,20] 是右子树,[9] 是左子树
     * 4. 将 [9] 和 [15,20,7]/[15,7,20] 视为两个树,同理,根节点分别是[9] 和 [20],此时[3]、[9]、[20] 就可以用来组成二叉树
     * 5. 不断以1、2、3步进行递归,找到所以子树的根节点,组成二叉树
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        TreeNode root = recursion(inorder, postorder, 0, inorder.length-1,
                0, postorder.length-1);
        return root;
    }

    private TreeNode recursion(int[] inorder, int[] postorder, int inOrderStartIndex,
        int inOrderEndIndex, int postOrderStartIndex, int postOrderEndIndex) {
        // 退出条件
        if (inOrderStartIndex > inOrderEndIndex || postOrderStartIndex >  postOrderEndIndex) {
            return null;
        }
        // 当前树的根节点值
        int rootVal = postorder[postOrderEndIndex];
        TreeNode rootNode = new TreeNode(rootVal);
        // 在 inorder 数组找到 rootVal 下标位置
        int inOrderRootIndex = search(inorder, inOrderStartIndex, inOrderEndIndex, rootVal);
        // 在 postorder 数组中,右子树节起始下标
        int postOrderRightTreeStartIndex = postOrderEndIndex - (inOrderEndIndex - inOrderRootIndex);
        // 递归左子树
        TreeNode leftNode = recursion(inorder, postorder, inOrderStartIndex, inOrderRootIndex-1,
                postOrderStartIndex, postOrderRightTreeStartIndex - 1);
        if (leftNode != null) {
            rootNode.left = leftNode;
        }
        // 递归右子树
        TreeNode rightNode = recursion(inorder, postorder, inOrderRootIndex+1, inOrderEndIndex,
                postOrderRightTreeStartIndex, postOrderEndIndex - 1);
        if (rightNode != null) {
            rootNode.right = rightNode;
        }
        return rootNode;
    }

    private int search(int[] inorder, int inOrderStartIndex, int inOrderEndIndex, int rootVal)  {
        for (int i = inOrderStartIndex; i <= inOrderEndIndex; i++) {
            if (inorder[i] == rootVal) {
                return i;
            }
        }
        return -1;
    }
}
