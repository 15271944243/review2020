package review.acm;

import java.util.Scanner;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/5/15 14:56
 */
public class Test {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean[] arr = new boolean[1001];
        boolean modify = false;
        int m = 0;
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            if (str.isEmpty()) {
                break;
            }
            int n = Integer.parseInt(str);
            if (m == 0) {
                m = n;
                if (modify) {
                    for(int i=0;i<arr.length;i++) {
                        if (arr[i]) {
                            System.out.println(i);
                        }
                    }
                    arr = new boolean[1001];
                }
                continue;
            }
            arr[n] = true;
            m--;
            modify = true;
        }
        for(int i=0;i<arr.length;i++) {
            if (arr[i]) {
                System.out.println(i);
            }
        }
    }
}
