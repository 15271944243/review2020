package review.sort;

import review.utils.StrUtils;

import java.util.ArrayList;

/**
 * 桶排序
 * @author: xiaoxiaoxiang
 * @date: 2020/10/26 10:51
 */
public class BucketSort {

    /**
     * 计数排序:
     * 时间复杂度(平均): O(n+k)
     * 时间复杂度(最坏): O(n^2)
     * 时间复杂度(最好): O(n)
     * 空间复杂度: O(n+k)
     * 稳定性: 稳定
     *
     * - 稳定: 如果a原本在b前面,而a=b,排序之后a仍然在b的前面
     * - 不稳定: 如果a原本在b的前面,而a=b,排序之后 a 可能会出现在 b 的后面
     */
    public static void main(String[] args) {
        BucketSort bucketSort = new BucketSort();
        int[] a = {30,40,60,30,14,28,77,10,20,50,89,103};
        bucketSort.bucketSort(a);
        System.out.println(StrUtils.arrayToString(a, ","));
    }

    /**
     * 桶排序是计数排序的升级版.
     * 它利用了函数的映射关系,高效与否的关键就在于这个映射函数的确定.
     * 桶排序(Bucket sort)的工作的原理: 假设输入数据服从均匀分布,将数据分到有限数量的桶里,
     * 每个桶内元素再分别排序(有可能再使用别的排序算法或是以递归方式继续使用桶排序进行排)
     *
     * 步骤:
     * 1. 设置一个定量的数组当作空桶
     * 2. 遍历输入数据,并且把数据一个一个放到对应的桶里去
     * 3. 对每个不是空的桶的元素进行排序
     * 4. 从不是空的桶里把排好序的数据拼接起来
     *
     * 桶排序需要尽量保证元素分散均匀,否则当所有数据集中在同一个桶中时,桶排序失效
     */
    public void bucketSort(int[] arr){
        if (arr == null || arr.length < 2) {
            return;
        }
        // 0. 计算最大值与最小值,方便计算桶对数量
        int max = arr[0];
        int min = arr[0];
        for (int i=1;i<arr.length;i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
            if (min > arr[i]) {
                min = arr[i];
            }
        }
        // 1. 计算桶的数量
        int bucketNum = (max - min) / arr.length + 1;
        ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketNum);
//        ArrayList<Integer> resultArr = new ArrayList<>();
        for (int i=0;i<bucketNum;i++) {
            bucketArr.add(new ArrayList<>());
        }
        // 2. 将数据放入对应的桶中
        for (int i=0;i<arr.length;i++) {
            int num = (arr[i] - min) / arr.length;
            bucketArr.get(num).add(arr[i]);
        }

        // 3. 对每个不是空的桶的元素进行排序
        for (ArrayList<Integer> arrayList : bucketArr) {
            if (arrayList.size() > 0) {
                int[] tmpArr = toArray(arrayList);
                quickSort(tmpArr, 0, tmpArr.length - 1);
                arrayList.clear();
                toArrayList(arrayList, tmpArr);
            }
        }

        // 4.从所有桶中取出已排好序对元素
        int k = 0;
        for (ArrayList<Integer> arrayList : bucketArr) {
            for (Integer integer : arrayList) {
                arr[k++] = integer;
            }
        }
    }

    private int[] toArray(ArrayList<Integer> list) {
        int[] arr = new int[list.size()];
        int i = 0;
        for (Integer integer : list) {
            arr[i++] = integer;
        }
        return arr;
    }

    private void toArrayList(ArrayList<Integer> arrayList, int[] arr) {
        for (int i=0;i<arr.length;i++) {
            arrayList.add(arr[i]);
        }
    }

    private void quickSort(int[] arr, int left, int right) {
        if (arr == null || arr.length < 2) {
            return;
        }
        if(left >= right){
            return;
        }
        int pivotIndex = partition(arr, left, right);
        quickSort(arr, left, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, right);
    }

    private int partition(int[] arr, int left, int right) {
        int pivot = arr[left];
        int bigNumIndex = left + 1;

        for (int i=left + 1;i<=right;i++) {
            if (arr[i] < pivot) {
                int tmp = arr[i];
                arr[i] = arr[bigNumIndex];
                arr[bigNumIndex] = tmp;
                bigNumIndex++;
            }
        }
        int tmp = arr[bigNumIndex - 1];
        arr[bigNumIndex - 1] = arr[left];
        arr[left] = tmp;
        return bigNumIndex - 1;
    }
}
