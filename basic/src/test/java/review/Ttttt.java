package review;

import org.junit.Test;

import java.util.HashSet;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/1/17 16:02
 */
public class Ttttt {

    /**
     * 异或
     */
    @Test
    public void test_xor() {
        int a = 1, b = 2;
        a=a^b;
        b=b^a;
        a=a^b;

        System.out.println(a);
        System.out.println(b);
        System.out.println(a^b^b);

        System.out.println(a^a);
        System.out.println(a^0);
    }

    /**
     * 移位
     */
    @Test
    public void test_shifting() {
        int a = -2, b = -1, c = 3, d = 5;

        System.out.println(a>>>31);
        System.out.println(b>>>31);
        System.out.println(c>>>31);
        System.out.println(d >> 1);
        System.out.println(d >> 33);
    }

    @Test
    public void test_shifting2() {
        // -1 原码: 1000 ... 0001 补码: 1111 ... 1111
        char c1=(char)-1;
        System.out.println(Integer.toBinaryString(c1));
        c1>>=10;
        System.out.println(Integer.toBinaryString(c1));
        char c2=(char)-1;
        c2>>=17;
        System.out.println(Integer.toBinaryString(c2));
        char c3=(char)-1;
        c3>>=65;
        System.out.println(Integer.toBinaryString(c3));
        char u_c=(char)-1;
        u_c>>>=10;
        System.out.println(Integer.toBinaryString(u_c));
        short s=(short)-1;
        System.out.println(Integer.toBinaryString(s));
        s>>=33;
        System.out.println(Integer.toBinaryString(s));
    }

    /**
     * 值传递和引用传递
     */
    @Test
    public void test_valueTransmit() {
        int num = 1;
        foo(num);
        String str = "123";
        foo(str);
        StringBuilder sb = new StringBuilder("iphone");
        foo(sb);
        StringBuilder sb2 = new StringBuilder("iphone");
        foo2(sb2);
        System.out.println("num=" + num);
        System.out.println("str=" + str);
        System.out.println("sb=" + sb.toString());
        System.out.println("sb2=" + sb2.toString());

        int[][] arr2 = new int[2][4];
        int[][] arr3 = new int[3][];
        arr3[0] = new int [5];
        arr3[1] = arr2[0];
    }

    private void foo(int num) {
        num = 123123;
    }

    private void foo(String str) {
        str = "xxxxx";
    }

    private void foo(StringBuilder builder) {
        builder.append("4");
    }

    private void foo2(StringBuilder builder) {
        builder = new StringBuilder("ipad");
    }

    /**
     * hashCode与equals的关系
     */
    @Test
    public void test_hashCodeAndEquals() {
        User user1 = new User("1");
        User user2 = new User("1");

        HashSet<User> set = new HashSet<>();
        set.add(user1);
        set.add(user2);
        System.out.println(set.size());
    }

    class User {

        private String id;

        public User(String id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            User user = (User) o;

            return id != null ? id.equals(user.id) : user.id == null;
        }

        @Override
        public int hashCode() {
            return id != null ? id.hashCode() : 0;
        }
    }
}
