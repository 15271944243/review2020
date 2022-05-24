package review.linkedlist;

/**
 * https://leetcode.cn/problems/reverse-linked-list/ No.206 反转链表
 * @author: xiaoxiaoxiang
 * @date: 2022/5/24 8:05
 */
public class ReverseLinkedList {

    /**
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表
     *
     * 输入：head = [1,2,3,4,5]
     * 输出：[5,4,3,2,1]
     *
     * 输入：head = [1,2]
     * 输出：[2,1]
     *
     * 输入：head = []
     * 输出：[]
     *
     * 链表中节点的数目范围是 [0, 5000]
     * -5000 <= Node.val <= 5000
     *
     * 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
     *
     */
    public static void main(String[] args) {
        ReverseLinkedList func = new ReverseLinkedList();
        ListNode head = func.getListNode();
        ListNode node = func.reverseList(head);
        System.out.println(111);
    }

    /**
     * 思路:
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode t = new ListNode();
        ListNode r = head;
        while (r != null) {
            ListNode a = r.next;
            ListNode b = t.next;
            r.next = b;
            t.next = r;
            r = a;
        }
        return t.next;
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
