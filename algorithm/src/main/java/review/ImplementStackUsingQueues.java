package review;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * https://leetcode.com/problems/implement-stack-using-queues/
 * @author: xiaoxiaoxiang
 * @date: 2020/5/14 23:39
 */
public class ImplementStackUsingQueues {

    /**
     * Implement the following operations of a stack using queues.
     *
     * push(x) -- Push element x onto stack.
     * pop() -- Removes the element on top of the stack.
     * top() -- Get the top element.
     * empty() -- Return whether the stack is empty.
     */

    /**
     * 题目意思: 使用队列(FIFO)实现栈(FILO)
     * 思路: 使用两个队列,将原始数据经过 入队 -> 出队, 然后再 入队 -> 出队,
     * 直到第一个队列只剩下一个数据,这个数据就是栈顶
     */

    public static void main(String[] args) {
        MyStack obj = new MyStack();
        obj.push(1);
        obj.push(2);
        obj.push(3);
        System.out.println(obj.pop());
        System.out.println(obj.top());
        obj.push(4);
        System.out.println(obj.pop());
        System.out.println(obj.top());
        System.out.println(obj.empty());
    }

    static class MyStack {
        LinkedBlockingQueue<Integer> queue1;
        LinkedBlockingQueue<Integer> queue2;

        /** Initialize your data structure here. */
        public MyStack() {
            queue1 = new LinkedBlockingQueue<>();
            queue2 = new LinkedBlockingQueue<>();
        }

        /** Push element x onto stack. */
        public void push(int x) {
            queue1.offer(x);
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            Integer last = null;
            while (!queue1.isEmpty()) {
                last = queue1.poll();
                if (!queue1.isEmpty()) {
                    // 队列最后一个元素不放入queue2
                    queue2.offer(last);
                }
            }
            while (!queue2.isEmpty()) {
                // 数据从queue1放回到queue2
                queue1.offer(queue2.poll());
            }
            return last;
        }

        /** Get the top element. */
        public int top() {
            Integer last = null;
            while (!queue1.isEmpty()) {
                last = queue1.poll();
                if (!queue1.isEmpty()) {
                    // 队列最后一个元素不放入queue2
                    queue2.offer(last);
                }
            }
            while (!queue2.isEmpty()) {
                // 数据从queue1放回到queue2
                queue1.offer(queue2.poll());
            }
            if (last != null) {
                queue1.offer(last);
            }
            return last;
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return queue1.isEmpty();
        }
    }
}
