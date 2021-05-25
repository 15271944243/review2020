package review.sort;

import review.utils.StrUtils;

/**
 * 选择排序
 * @author: xiaoxiaoxiang
 * @date: 2020/10/19 10:47
 */
public class SelectionSort {

    /**
     * 选择排序:
     * 时间复杂度(平均): O(n^2)
     * 时间复杂度(最坏): O(n^2)
     * 时间复杂度(最好): O(n^2)
     * 空间复杂度: O(1)
     * 稳定性: 不稳定
     *
     * - 稳定: 如果a原本在b前面,而a=b,排序之后a仍然在b的前面
     * - 不稳定: 如果a原本在b的前面,而a=b,排序之后 a 可能会出现在 b 的后面
     */

    public static void main(String[] args) {
        SelectionSort selectionSort = new SelectionSort();
        int[] a = {20,40,30,10,60,50};
        selectionSort.selectionSort2(a);
        System.out.println(StrUtils.arrayToString(a, ","));
    }

    /**
     * 基本思想是:
     * 首先在未排序序列中找到最小（大）元素,存放到排序序列的起始位置;
     * 然后,再从剩余未排序元素中继续寻找最小(大)元素,然后放到已排序序列的末尾.
     * 以此类推,直到所有元素均排序完毕.
     * @param arr
     */
    public void selectionSort(int[] arr) {
        for (int i=arr.length-1; i>0; i--) {
            int maxIndex = 0;
            for (int j=0; j<=i;j++) {
                if (arr[j] > arr[maxIndex]) {
                    maxIndex = j;
                }
            }
            // 注: 这里不能使用异或来交换值,因为maxIndex=i的情况下, arr[maxIndex] ^ arr[i] = 0
            StrUtils.swap(arr, maxIndex, i);
        }
    }


    /**
     * 选择排序:
     * 1. 找到数组中最小的元素,拎出来,将它和数组的第一个元素交换位置
     * 2. 在剩下的元素中继续寻找最小的元素,拎出来,和数组的第二个元素交换位置
     * 3. 如此循环,直到整个数组排序完成
     * @param arr
     */
    public void selectionSort2(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            int tmp = arr[i];
            arr[i] = arr[min];
            arr[min] = tmp;
        }
    }
}
