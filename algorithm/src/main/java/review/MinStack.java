package review;

import java.util.Stack;

/**
 * 最小栈的最优解
 */
public class MinStack {

	/**
	 * 实现一个这样的栈,这个栈除了可以进行普通的push、pop操作以外,还可以进行getMin的操作,getMin()方法被调用后,会返回当前栈的最小值.
	 * 栈里面存放的都是 int 整数,并且数值的范围是 [-100000, 100000].要求所有操作的时间复杂度是 O(1)
	 *
	 * - 时间复杂度 O(n) + 空间复杂度 O(1): 这个很简单,就是getMin()时候,每次都遍历一次栈,把最小栈返
	 *
	 * - 时间复杂度 O(1) + 空间复杂度 O(n): 有两个栈 stack 和 helper,stack 是目标栈,helper 是辅助栈,用来存放最小值;
	 * 每次 getMin() 的时候,直接从 helper 栈顶获取即可
	 *
	 * 每次进行 push 操作的时候,进行如下操作（假设要 push 的元素是 t）
	 * 1、对于 stack 栈,我们按照正常情况把元素 push 到栈顶就可以了
	 * 2、然后要把元素 t push 到 helper 栈顶的时候,要先把 t 与 helper 栈顶的元素（假设是 a）进行比较,如果 t <= a,
	 * 则把元素 t push 到 helper 的栈顶,如果 t > a，这个时候,我们不把 t push 进去,而是重复把 a push 到 helper 的栈顶
	 *
	 * 举个例子,例如我们要把数组 arr = {2, 1, 3} 都放入栈中,则存放过程如下:
	 * 1、首先 push 2.由于刚开始 stack 和 helper 都是空的,所以直接把 2 放入,此时目标栈和辅助栈的值如下: stack = {2},helper = {2}
	 * 2、接下来 push 1,由于 helper 栈顶元素比 1 大,所以直接把 1 放入 helper 的栈顶,此时: stack = {2, 1},helper = {2, 1}
	 * 3、接下来 push 3,由于 helper 栈顶元素比 3 小,所以重复把 栈顶的元素再次入栈,此时:stack = {2, 1, 3},helper = {2, 1, 1}
	 *
	 * - 时间复杂度 O(1) + 空间复杂度 O(1): 思路如下
	 * stack 栈中,不能存放原始数值,而是应该存放 差值,就是存放栈顶与最小值的差值
	 *
	 * 例如 arr = {2, 1, 3, 0},那么把这些元素入栈时,stack 栈中元素以及最小值的变化如下
	 *
	 * 入栈元素   实际入栈的差值   最小值
	 *   2           0          2
	 *   1          -1(1-2)     1
	 *   3          2(3-1)      1
	 *   0          -1(0-1)     0
  	 *
	 * 具体参考下面的代码
	 * 这个方案有个前提条件,即数据有范围限制,因为数值没有限制的话,差值的计算可能会溢出
	 */

	public static void main(String[] args) {
		MinStack demo = new MinStack();
		demo.push(2);
		demo.push(1);
		demo.push(3);
		demo.push(0);
		System.out.println(demo.getMin());
		System.out.println(demo.pop());
		System.out.println(demo.pop());
		System.out.println(demo.pop());
		System.out.println(demo.pop());
	}


	private Stack<Integer> stack = new Stack<>();

	private int min;

	public void push(int x) {
		if (stack.isEmpty()) {
			min = x;
			stack.push(0);
		} else {
			// 计算差值
			int compare = x - min;
			stack.push(compare);
			// 如果差值小于0，显然 x 成为最小值，否则最小值不变
			min = compare < 0 ? x : min;
		}
	}

	public int pop() {
		int top = stack.peek();
		// 如果top小于0，是当前真实值被用来计算差值的最小值小,然后用当前真实值替换了最小值
		// 所有当前真实值应该就是当前最小值 min,用来计算差值的最小值就是 当前最小值 min - top
		// 此时更新最小值
		min = top < 0 ? (min - top) : min;
		stack.pop();
		return top + min;
	}

	public int getMin() {
		return min;
	}

}