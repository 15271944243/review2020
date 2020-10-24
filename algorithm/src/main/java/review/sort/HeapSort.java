package review.sort;

import review.utils.StrUtils;

/**
 * 堆排序
 * @author: xiaoxiaoxiang
 * @date: 2020/10/24 15:04
 */
public class HeapSort {

    /**
     * 堆排序:
     * 时间复杂度(平均): O(nlog2n)
     * 时间复杂度(最坏): O(nlog2n)
     * 时间复杂度(最好): O(nlog2n)
     * 空间复杂度: O(1)
     * 稳定性: 不稳定
     *
     * - 稳定: 如果a原本在b前面,而a=b,排序之后a仍然在b的前面
     * - 不稳定: 如果a原本在b的前面,而a=b,排序之后 a 可能会出现在 b 的后面
     */
    public static void main(String[] args) {
        HeapSort heapSort = new HeapSort();
        int[] a = {30,40,60,30,14,28,77,10,20,50,89,103};
        heapSort.heapSort(a);
        System.out.println(StrUtils.arrayToString(a, ","));

        int[] b = {40,30};
        heapSort.heapSort(b);
        System.out.println(StrUtils.arrayToString(b, ","));

        int[] c = {40,30,15};
        heapSort.heapSort(c);
        System.out.println(StrUtils.arrayToString(c, ","));
    }

    /**
     * 基本思想:
     * 将待排序序列构造成一个大顶堆,此时,整个序列的最大值就是堆顶的根节点.将根节点与末尾元素进行交换,此时末尾就为最大值.
     * 然后将剩余n-1个元素重新构造成一个堆,这样会得到n个元素的次小值，如此反复执行,便能得到一个有序序列了
     */

    public void heapSort(int[] arr){
        if (arr == null || arr.length < 2) {
            return;
        }

        int maxLength = arr.length;
        // 将原始数组构建为堆
        // 最后一个非叶子结点的位置为 arr.length / 2 (向下取整) (位置从1开始计算)
        for (int k = arr.length / 2;k > 0; k--) {
            shift(arr, k, maxLength);
        }

        // 开始进行堆排序
        for (int i = arr.length - 1; i> 0; i--) {
            // 首位和末尾交换位置
            StrUtils.swap(arr, 0, i);
            shift(arr, 1, i);
        }
    }

    /**
     * 大顶堆-结点移动
     * @param arr
     * @param i         非叶子结点的位置(从1开始计算),判断非叶子结点是否是最小值
     * @param maxLength 数组可用最大长度(已排好序的元素就不要在移动了)
     */
    private void shift(int[] arr, int i, int maxLength) {
        // 找到i位置元素的左子节点和右子节点的位置(这里的索引都是从1开始)
        int left = 2 * i;
        int right = left + 1;
        // 转化为从0开始的索引,方便数组使用
        int index = i - 1;
        // 最小值的索引
        int biggestIndex = - 1;
        // 判断是否存在左子节点和右子节点
        if (left == maxLength) {
            // 右子节点不存在
            // 转化为从0开始的索引,方便数组使用
            left--;
            if (arr[left] > arr[index]) {
                biggestIndex = left;
            }
        } else if (left < maxLength) {
            // 左子节点和右子节点都存在
            // 转化为从0开始的索引,方便数组使用
            left--;
            right--;
            int maxIndex = arr[left] >= arr[right] ? left : right;
            if (arr[maxIndex] > arr[index]) {
                biggestIndex = maxIndex;
            }
        } else {
            // 左子节点和右子节点都不存在
        }
        if (biggestIndex > -1) {
            StrUtils.swap(arr, biggestIndex, index);
            // 继续递归判断,位置从1开始计算,所以要加1
            shift(arr, biggestIndex + 1, maxLength);
        }
    }
}
