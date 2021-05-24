package review.linkedlist;

/**
 * https://leetcode.com/problems/linked-list-cycle/ No.141 环形链表
 * @author: xiaoxiaoxiang
 * @date: 2021/5/24 18:09
 */
public class LinkedListCycle {

    /**
     * 给定一个链表，判断链表中是否有环。
     *
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
     * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
     *
     * 如果链表中存在环，则返回 true 。 否则，返回 false 。
     *
     * 思路: 双指针法
     */
    public static void main(String[] args) {
        LinkedListCycle demo = new LinkedListCycle();
        ListNode head = demo.getListNodes();
        boolean result = demo.hasCycle(head);
    }

    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode p1 = head;
        ListNode p2 = head.next;
        boolean result = false;
        while (true) {
            if (p1 == null || p2 == null) {
                break;
            }
            if (p1 == p2) {
                result = true;
                break;
            }
            p1 = p1.next;
            p2 = p2.next == null ? null : p2.next.next;
        }
        return result;
    }

    private ListNode getListNodes() {
        ListNode head = new ListNode(3);
        ListNode node1 = new ListNode(2);
        head.next = node1;
        ListNode node2 = new ListNode(0);
        node1.next = node2;
        ListNode node3 = new ListNode(3);
        node2.next = node3;
        node3.next = node1;
        return head;
    }
}