package review.sort;

import review.utils.StrUtils;

/**
 * @description: 插入排序
 * @author: xiaoxiaoxiang.
 * @createDate: 2018/5/22
 */
public class InsertionSort {

    /**
     * 插入排序:
     * 时间复杂度(平均): O(n^2)
     * 时间复杂度(最坏): O(n^2)
     * 时间复杂度(最好): O(n)
     * 空间复杂度: O(1)
     * 稳定性: 稳定
     *
     * - 稳定: 如果a原本在b前面,而a=b,排序之后a仍然在b的前面
     * - 不稳定: 如果a原本在b的前面,而a=b,排序之后 a 可能会出现在 b 的后面
     */
    public static void main(String[] args) {
        InsertionSort insertionSort = new InsertionSort();
        int[] a = {30,40,60,30,14,28,77,10,20,50,89,103};
        insertionSort.insertionSort(a);
        System.out.println(StrUtils.arrayToString(a, ","));
    }

    /**
     * 基本思想是：把n个待排序的元素看成为一个有序表和一个无序表。开始时有序表中只包含1个元素，无序表中包含有n-1个元素，
     * 排序过程中每次从无序表中取出第一个元素，将它插入到有序表中的适当位置，使之成为新的有序表，重复n-1次可完成排序过程。
     * 非常类似于我们抓扑克牌.
     * @param arr  待排序的数组
     */
    public void insertionSort(int[] arr){
        // 类似抓扑克牌排序
        for (int i=1; i< arr.length;i++) {
            // 右手抓到一张扑克牌
            int tmp = arr[i];
            for (int j=i-1; j >=0; j--) {
                // 将抓到的牌与手牌从右向左进行比较
                if (tmp < arr[j]) {
                    // 如果最右边的牌(即最大的牌)比抓到的牌大,就将其右移
                    arr[j+1] = arr[j];
                    // 将抓到的牌放到该位置
                    arr[j] = tmp;
                }
            }
        }
    }
}
