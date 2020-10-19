package review.utils;

import sun.net.www.content.text.Generic;

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
}
