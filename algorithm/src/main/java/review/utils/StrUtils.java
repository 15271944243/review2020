package review.utils;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/10/19 14:09
 */
public final class StrUtils {

    private StrUtils() {
    }

    public static String arrayToString(int[] arr, String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i< arr.length; i++) {
            sb.append(arr[i])
                    .append(delimiter);
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
