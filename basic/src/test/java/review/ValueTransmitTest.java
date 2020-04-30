package review;

import org.junit.Test;

/**
 * java是值传递,不是引用传递,因为传的都是实参的拷贝
 * @author: xiaoxiaoxiang
 * @date: 2020/1/20 15:36
 */
public class ValueTransmitTest {

    @Test
    public void test_valueTransmit1() {
        // 传递参数是值类型
        int i = 1;
        pass1(i);
        System.out.println("main: i= " + i);
        // 传递参数是引用类型
        User user1 = new User("xxx");
        pass2(user1);
        System.out.println("main: user name is " + user1.getName());
        // 传递参数是引用类型
        User user2 = new User("xxx");
        pass3(user2);
        System.out.println("main: user2 name is " + user2.getName());

    }

    private void pass1(int i) {
        i += 1;
        System.out.println("pass1: i= " + i);
    }

    private void pass2(User user) {
        user.setName("xiao");
        System.out.println("pass2: user name is " + user.getName());
    }

    private void pass3(User user) {
        user = new User();
        user.setName("xiaoxiao");
        System.out.println("pass3: user2 name is " + user.getName());
    }

    class User {
        private String name;

        public User() {
        }

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
