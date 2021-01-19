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

    public static void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 反转字符串
     * @param str
     * @return
     */
    public static String reverse(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        char[] chars = str.toCharArray();
        int head = 0;
        int tail = chars.length - 1;
        for (int i=0;i<chars.length;i++) {
            // 当头指针小于等于尾指针时停止循环
            if (head >= tail) {
                break;
            }
            swap(chars, head, tail);
            head++;
            tail--;
        }
        return new String(chars);
    }
}
