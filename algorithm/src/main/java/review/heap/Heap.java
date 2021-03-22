package review.heap;

import review.utils.StrUtils;

/**
 * 数据结构-堆  优先队列就是采用堆实现,因为优先级队列对外接口只是从队头取元素,从队尾添加元素
 * @author: xiaoxiaoxiang
 * @date: 2020/10/23 15:16
 */
public class Heap {

    /**
     * 内部数组
     */
    private int[] arr;

    /**
     * 大顶堆/小顶堆
     */
    private boolean isBigTop;

    public Heap(int[] arr) {
        this(arr, false);
    }

    /**
     * 堆(Heap)是计算机科学中一类特殊的数据结构的统称.堆通常是一个可以被看做一棵完全二叉树的数组对象.
     * - 堆中某个节点的值总是不大于或不小于其父节点的值
     * - 堆是一棵完全二叉树结构维护的数组对象
     */
    public Heap(int[] arr, boolean isBigTop) {
        if (arr != null) {
            this.arr = new int[arr.length];
            System.arraycopy(arr, 0, this.arr, 0, arr.length);
        }
        this.isBigTop = isBigTop;
        if (this.isBigTop) {
            // TODO 大顶堆的实现
        } else {
            buildSmallTopHeap(this.arr);
        }
    }

    /**
     * 创建小顶堆
     * @param arr  原始乱序数组,需要将它组成小顶堆
     * @return
     */
    private void buildSmallTopHeap(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 最后一个非叶子结点的位置(索引从1开始) k = [arr.length / 2] ([]表示向下取整)
        int k = arr.length / 2;
        // 从最后一个非叶子结点开始判断是否符合规则
        for (;k > 0;k--) {
            smallTopNodeShift(arr, k);
        }
    }

    /**
     * 插入-向堆的尾部插入
     * @param num
     */
    public void insert(int num) {
        // 向堆的尾部插入,数组需要扩容
        int[] newArr = new int[this.arr.length + 1];
        System.arraycopy(this.arr, 0, newArr, 0, this.arr.length);
        newArr[newArr.length - 1] = num;
        // 判断新插入元素是否需要上移(从1开始计算位置)
        for (int k = newArr.length / 2;k > 0; k = k /2) {
            if (this.isBigTop) {
                // TODO 大顶堆的实现
            } else {
                smallTopNodeShift(newArr, k);
            }
        }
        this.arr = newArr;
    }

    /**
     * 弹出并删除堆顶元素
     * @return
     */
    public int pop() {
        int r = this.arr[0];
        int[] newArr = new int[this.arr.length - 1];
        // 删除第一个元素的方式: 删除第一个元素,并将最后一个元素放入首位
        // 这个做是为了保证堆还是完全二叉树的结构
        newArr[0] = this.arr[this.arr.length - 1];
        System.arraycopy(this.arr, 1, newArr, 1, this.arr.length - 2);
        // 因为首位元素是从末尾获取的,所以要判断首位元素是否复合堆的条件
        if (this.isBigTop) {
            // TODO 大顶堆的实现
        } else {
            smallTopNodeShift(newArr, 1);
        }
        this.arr = newArr;
        return r;
    }


    /**
     * 小顶堆-结点移动
     * @param arr
     * @param i      非叶子结点的位置(从1开始计算),判断非叶子结点是否是最小值
     */
    private void smallTopNodeShift(int[] arr, int i) {
        // 找到i位置元素的左子节点和右子节点的位置(这里的索引都是从1开始)
        int left = 2 * i;
        int right = left + 1;
        // 转化为从0开始的索引,方便数组使用
        int index = i - 1;
        // 最小值的索引
        int samllestIndex = - 1;
        // 判断是否存在左子节点和右子节点
        if (left == arr.length) {
            // 右子节点不存在
            // 转化为从0开始的索引,方便数组使用
            left--;
            if (arr[left] < arr[index]) {
                samllestIndex = left;
            }
        } else if (left < arr.length) {
            // 左子节点和右子节点都存在
            // 转化为从0开始的索引,方便数组使用
            left--;
            right--;
            int minIndex = arr[left] <= arr[right] ? left : right;
            if (arr[minIndex] < arr[index]) {
                samllestIndex = minIndex;
            }
        } else {
            // 左子节点和右子节点都不存在
        }
        if (samllestIndex > -1) {
            StrUtils.swap(arr, samllestIndex, index);
            // 继续递归判断,索引从1开始
            smallTopNodeShift(arr, samllestIndex + 1);
        }
    }

    public int[] getArr() {
        return arr;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    public boolean isBigTop() {
        return isBigTop;
    }

    public void setBigTop(boolean bigTop) {
        isBigTop = bigTop;
    }


    // 求树的总高度 h = [log2n] + 1  (注: n 表示总结点数,[]表示向下取整)  但是我们这里用的位运算求的总高度
    // 换底公式 logx(y) =loge(y) / loge(x)
    // 最后一个非叶子结点的高度 = 总高度 - 1 (高度从1开始算)
//    int h = getHeight(arr.length) - 1;
    // 最后一个非叶子结点的索引(从1开始算) k = (2^n) - 1
//    int k = (1 << h) - 1;

    private int getHeight(int n) {
        for (int i=0;i < 32;i++) {
            if (n >> i == 0) {
//                System.out.println("n: " + n + ",i: " + i);
                return i;
            }
        }
        return 0;
    }

}
