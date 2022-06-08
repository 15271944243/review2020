package review.linkedlist;

/**
 * https://leetcode.cn/problems/reverse-nodes-in-k-group/ No.25  K 个一组翻转链表
 * @author: xiaoxiaoxiang
 * @date: 2022/6/8 8:25
 */
public class ReverseNodesInKGroup {

    /**
     * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
     *
     * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     *
     * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
     *
     * 输入：head = [1,2,3,4,5], k = 2
     * 输出：[2,1,4,3,5]
     *
     * 输入：head = [1,2,3,4,5], k = 3
     * 输出：[3,2,1,4,5]
     *
     * 链表中的节点数目为 n
     * 1 <= k <= n <= 5000
     * 0 <= Node.val <= 1000
     *
     * 进阶：你可以设计一个只用 O(1) 额外内存空间的算法解决此问题吗？
     */

    public static void main(String[] args) {
        ReverseNodesInKGroup func = new ReverseNodesInKGroup();
        ListNode head = func.getListNode();
        int k = 9;
        ListNode result = func.reverseKGroup(head, k);
        System.out.println(111);
    }

    public ListNode reverseKGroup(ListNode head, int k) {

        return null;
    }

    private ListNode getListNode() {
        // [1,2,6,3,4,5,6]
        ListNode node7 = new ListNode(6);
        ListNode node6 = new ListNode(5, node7);
        ListNode node5 = new ListNode(4, node6);
        ListNode node4 = new ListNode(3, node5);
        ListNode node3 = new ListNode(6, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        return node1;
    }
}
