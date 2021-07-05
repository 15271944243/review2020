package review.linkedlist;

/**
 * https://leetcode.com/problems/add-two-numbers/ No.2  两数相加
 * @author: xiaoxiaoxiang
 * @date: 2021/5/27 14:47
 */
public class AddTwoNumbers {

    /**
     * You are given two non-empty linked lists representing two non-negative integers.
     * The digits are stored in reverse order, and each of their nodes contains a single digit.
     * Add the two numbers and return the sum as a linked list.
     *
     * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     *
     * Input: l1 = [2,4,3], l2 = [5,6,4]
     * Output: [7,0,8]
     * Explanation: 342 + 465 = 807.
     *
     * Input: l1 = [0], l2 = [0]
     * Output: [0]
     *
     * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
     * Output: [8,9,9,9,0,0,0,1]
     *
     * The number of nodes in each linked list is in the range [1, 100].
     * 0 <= Node.val <= 9
     * It is guaranteed that the list represents a number that does not have leading zeros.
     */

    /**
     * 题目意思:
     * 给你两个非空的链表,表示两个非负的整数.它们每位数字都是按照逆序的方式存储的,并且每个节点只能存储,一位数字
     *
     * 请你将两个数相加,并以相同形式返回一个表示和的链表
     *
     * 你可以假设除了数字 0 之外,这两个数都不会以 0 开头
     *
     */
    public static void main(String[] args) {
        AddTwoNumbers demo = new AddTwoNumbers();
        ListNode l1 = null;
        ListNode l2 = null;
        ListNode result = demo.addTwoNumbers(l1, l2);
    }


    /**
     * 思路: 直接两个同级节点相加
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p = l1;
        ListNode q = l2;
        ListNode head = new ListNode(0);
        ListNode n = head;
        // 进位
        int carry = 0;
        while (p != null || q != null) {
            int pValue = p == null ? 0 : p.val;
            int qValue = q == null ? 0 : q.val;

            int v = pValue + qValue + carry;
            if (v > 9) {
                carry = 1;
                v = v - 10;
            } else {
                carry = 0;
            }
            ListNode r = new ListNode(v);
            n.next = r;
            n = n.next;
            p = p == null ? null : p.next;
            q = q == null ? null : q.next;
        }
        if (carry != 0) {
            ListNode r = new ListNode(carry);
            n.next = r;
        }
        return head.next;
    }
}
