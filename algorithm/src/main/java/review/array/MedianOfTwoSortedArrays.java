package review.array;

/**
 * https://leetcode.cn/problems/median-of-two-sorted-arrays/ No.4 寻找两个正序数组的中位数
 * @author: xiaoxiaoxiang
 * @date: 2022/5/22 10:04
 */
public class MedianOfTwoSortedArrays {

    /**
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
     *
     * 算法的时间复杂度应该为 O(log (m+n)) 。
     *
     * 输入：nums1 = [1,3], nums2 = [2]
     * 输出：2.00000
     * 解释：合并数组 = [1,2,3] ，中位数 2
     *
     * 输入：nums1 = [1,2], nums2 = [3,4]
     * 输出：2.50000
     * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
     *
     * 提示：
     *
     * nums1.length == m
     * nums2.length == n
     * 0 <= m <= 1000
     * 0 <= n <= 1000
     * 1 <= m + n <= 2000
     * -10^6 <= nums1[i], nums2[i] <= 10^6
     */
    public static void main(String[] args) {
//        int[] nums1 = new int[]{1,2,5,7};
//        int[] nums2 = new int[]{3,6,9,12,15,18,20};
//        int[] nums1 = new int[]{1,2};
//        int[] nums2 = new int[]{3,4};
//        int[] nums1 = new int[]{2,2,2};
//        int[] nums2 = new int[]{2,2,2,2,2};
        int[] nums1 = new int[]{1};
        int[] nums2 = new int[]{2,3,4,5,6,7};
        MedianOfTwoSortedArrays demo = new MedianOfTwoSortedArrays();
        double result = demo.findMedianSortedArrays(nums1, nums2);
        System.out.println(result);
    }

    /**
     * 思路:
     * 将 nums1、nums2 合并，找到中位数,即二分的思想
     * 奇数长度: (l1 +l2 + 1)/2
     * 偶数长度: (l1 +l2 + 1)/2 + (l1 +l2 + 2)/2)/2
     * 合并一下: ((l1 +l2 + 1)/2 + (l1 +l2 + 2)/2)/2
     *
     * nums1、nums2 是有序的
     * 以 int[] nums1 = new int[]{1,2,5,7}; 、 int[] nums2 = new int[]{3,6,9,12,15,18,20}; 为例
     * 合并后的中位数索引 k = (l1 + l2)/2 = 6
     * 因为是两个数组合并的,所以我们把 k/2 = 3,那么在 nums1[0~2], mums2[0~2] 中较小的 3个元素是一定小于中位数的，就将这三个元素剔除,
     * 那此时 k = 6-3 = 3, 再复用上述逻辑,将 k/2 = 1
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        if (l1 == 0) {
            // 奇偶
            return (l2 & 1) == 1 ? nums2[l2/2] : (nums2[(l2-1)/2] + nums2[l2/2])/2d;
        }
        if (l2 == 0) {
            return (l1 & 1) == 1 ? nums1[l1/2] : (nums1[(l1-1)/2] + nums1[l1/2])/2d;
        }
        // 如果 k1 != k2,说明有两个中位数
        int k1 = (l1 + l2 + 1)/2;
        int k2 = (l1 + l2 + 2)/2;
        boolean flag = k1 != k2;
        // 记录剔除元素的位置
        int begin1 = 0;
        int begin2 = 0;
        // 对 k1 循环就行了
        while (true) {
            int k = k1/2;
            if (k == 0) {
                break;
            }
            // 在数组1找第 k-1 元素
            int num1 = k > l1 ? nums1[nums1.length - 1] : nums1[begin1 + k - 1];
            int num2 = k > l2 ? nums2[nums2.length - 1] : nums2[begin2 + k - 1];
            if (l1 == 0) {
                begin2 += k;
                l2 -= k;
            } else if (l2 == 0) {
                begin1 += k;
                l1 -= k;
            } else if (num1 <= num2) {
                // 剔除较小的那部分
                begin1 += k;
                l1 -= k;
            } else {
                begin2 += k;
                l2 -= k;
            }
            // 继续循环处理
            k1 -= k;
        }
        // 比较 begin1、begin2 索引位置的大小
        if (flag) {
            // 判断某个数组是不是全部都被过滤了
            if (begin1 >= nums1.length) {
                return (nums2[begin2] + nums2[begin2+1])/2d;
            } else if (begin2 >= nums2.length) {
                return (nums1[begin1] + nums1[begin1+1])/2d;
            }
            // 两个中位数,那就是 begin1、begin1+1、begin2、begin2+1 这4个位置中最小的两个
            if (nums1[begin1] <= nums2[begin2]) {
                int n = begin1 + 1 < nums1.length ? Math.min(nums1[begin1 + 1], nums2[begin2]) : nums2[begin2];
                return (nums1[begin1]+ n)/2d;
            } else {
                int n = begin2 + 1 < nums2.length ? Math.min(nums2[begin2 + 1], nums1[begin1]) : nums1[begin1];
                return (nums2[begin2]+ n)/2d;
            }
        } else {
            // 一个中位数,那就从 begin1、begin2 位置取小的那个
            if (begin1 >= nums1.length) {
                return nums2[begin2];
            } else if (begin2 >= nums2.length) {
                return nums1[begin1];
            }
            return Math.min(nums1[begin1], nums2[begin2]);
        }
    }

    private int findK(int[] nums1, int[] nums2, int begin1, int begin2, int k) {
        int tmp_k = k / 2;
        // TODO 未完待续
        return 0;
    }
}
