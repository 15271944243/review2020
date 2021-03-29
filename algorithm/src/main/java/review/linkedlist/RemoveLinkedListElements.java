package review.linkedlist;

/**
 * https://leetcode.com/problems/remove-linked-list-elements/ No.203
 * @author: xiaoxiaoxiang
 * @date: 2021/3/29 09:58
 */
public class RemoveLinkedListElements {

    /**
     * Given the head of a linked list and an integer val, remove all the nodes of the
     * linked list that has Node.val == val, and return the new head.
     *
     * Input: head = [1,2,6,3,4,5,6], val = 6
     * Output: [1,2,3,4,5]
     *
     * Input: head = [], val = 1
     * Output: []
     *
     * Input: head = [7,7,7,7], val = 7
     * Output: []
     *
     * The number of nodes in the list is in the range [0, 10^4].
     * 1 <= Node.val <= 50
     * 0 <= k <= 50
     */

    /**
     * 题目意思: 给你一个链表的头节点 head 和一个整数 val,请你删除链表中所有满足 Node.val == val 的节点,并返回 新的头节点
     *
     * 约束条件:
     * 列表中的节点在范围 [0, 10^4] 内
     * 1 <= Node.val <= 50
     * 0 <= k <= 50
     *
     * 思路:
     *
     */
    public static void main(String[] args) {
        RemoveLinkedListElements demo = new RemoveLinkedListElements();
        ListNode head = demo.getListNode();
        int val = 6;
        ListNode node = demo.removeElements(head, val);
    }

    /**
     * 使用虚拟头节点
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode p = new ListNode(0, head);
        ListNode cur = p;
        while (cur.next != null) {
            if (cur.next.val == val) {
                ListNode next = cur.next;
                cur.next = next.next;
            } else {
                cur = cur.next;
            }
        }
        return p.next;
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

