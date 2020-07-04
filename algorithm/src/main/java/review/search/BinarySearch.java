package review.search;

/**
 * 二分查找
 * @author: xiaoxiaoxiang
 * @date: 2020/7/4 14:03
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] array = new int[]{1, 6, 9, 15, 24, 44 ,56, 57, 67, 71, 77, 85, 89, 91, 93, 95};
        int target = 95;
        BinarySearch search = new BinarySearch();
        System.out.println(search.binarySearch(array, target));
    }

    /**
     * 二分查找的array必须是有序的
     * @param array   从小到大排序
     * @param target  查找目标
     * @return 目标所在的索引,-1表示没找到
     */
    public int binarySearch(int[] array, int target) {
        int left = 0, right = array.length - 1;
        while(left <= right) {
            // 避免left + right可能会溢出,推荐mid = left + (right - left)
            int mid = left + (right - left) / 2;
            if (target == array[mid]) {
                return mid;
            } else if (target < array[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
