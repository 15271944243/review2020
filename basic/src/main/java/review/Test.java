package review;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/5/29 10:45
 */
public class Test {

    /*public static void main(String[] args) throws IOException {
        BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
        String a = bufr.readLine();
        BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(System.out));
        bufw.write(a);
        bufw.flush();
    }*/
    public static void main(String[] args) {
        boolean flag = true;
        if (flag) {
            System.out.println("Hello, Java!");
        }
        if (flag == true) {
            System.out.println("Hello, JVM!");
        }

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        for (Integer i : list) { // 第二次循环时 throw ConcurrentModificationException
            list.add(1);
        }

    }
}