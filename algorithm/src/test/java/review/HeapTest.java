package review;

import review.heap.Heap;
import review.utils.StrUtils;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/7/19 23:11
 */
public class HeapTest {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 8, 5, 1, 4, 7, 6, 9, 2};
        Heap heap = new Heap(arr);
        System.out.println(StrUtils.arrayToString(heap.getArr(), ","));
        heap.insert(11);
        System.out.println(StrUtils.arrayToString(heap.getArr(), ","));
        heap.insert(10);
        System.out.println(StrUtils.arrayToString(heap.getArr(), ","));
        heap.insert(3);
        System.out.println(StrUtils.arrayToString(heap.getArr(), ","));
        System.out.println(heap.pop());
        System.out.println(StrUtils.arrayToString(heap.getArr(), ","));
        System.out.println(heap.pop());
        System.out.println(StrUtils.arrayToString(heap.getArr(), ","));

        int[] arr2 = new int[]{8,3};
        Heap heap2 = new Heap(arr2);
        System.out.println(StrUtils.arrayToString(heap2.getArr(), ","));
    }
}
