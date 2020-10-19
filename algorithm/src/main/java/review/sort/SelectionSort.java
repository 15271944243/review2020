package review.sort;

import review.utils.StrUtils;

import java.util.Arrays;

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
        selectionSort.selectionSort(a);
        System.out.println(StrUtils.arrayToString(a, ","));
    }

    public void selectionSort(int[] arr) {
        for (int i=arr.length-1; i>0; i--) {
            int maxIndex = 0;
            for (int j=0; j<=i;j++) {
                if (arr[j] > arr[maxIndex]) {
                    maxIndex = j;
                }
            }
            // 注: 这里不能使用异或来交换值,因为maxIndex=i的情况下, arr[maxIndex] ^ arr[i] = 0
            int tmp = arr[maxIndex];
            arr[maxIndex] = arr[i];
            arr[i] = tmp;
        }
    }
}
