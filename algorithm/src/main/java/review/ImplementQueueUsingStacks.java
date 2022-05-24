package review;

import java.util.Stack;

/**
 * https://leetcode.com/problems/implement-queue-using-stacks/ NO.232 用栈实现队列
 * @author: xiaoxiaoxiang
 * @date: 2020/5/13 23:08
 */
public class ImplementQueueUsingStacks {
    /**
     * Implement the following operations of a queue using stacks.
     *
     * push(x) -- Push element x to the back of queue.
     * pop() -- Removes the element from in front of queue.
     * peek() -- Get the front element.
     * empty() -- Return whether the queue is empty.
     */

    /**
     * 题目意思: 使用栈(FILO)实现队列(FIFO)
     * 思路: 使用两个栈,将原始数据 入栈 -> 出栈, 然后再 入栈 -> 出栈;
     * 顺序就从FILO变成了FIFO
     */
    public static void main(String[] args) {
        MyQueue obj = new MyQueue();
        obj.push(1);
        obj.push(2);
        System.out.println(obj.peek());
        obj.push(3);
        System.out.println(obj.peek());
        System.out.println(obj.empty());
    }

    static class MyQueue {

        private Stack<Integer> stack1;
        private Stack<Integer> stack2;

        /** Initialize your data structure here. */
        public MyQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            while (!stack2.empty()) {
                stack1.push(stack2.pop());
            }
            stack1.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
            return stack2.pop();
        }

        /** Get the front element. */
        public int peek() {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
            return stack2.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return stack1.empty() && stack2.empty();
        }
    }
}
