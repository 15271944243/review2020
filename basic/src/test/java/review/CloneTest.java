package review;

import org.junit.Test;

/**
 * 深拷贝浅拷贝
 * @author: xiaoxiaoxiang
 * @date: 2020/1/20 15:36
 */
public class CloneTest {

    /**
     * 浅拷贝  被复制对象的所有变量都含有与原来的对象相同的值(包括对象对引用),为类似值传递;
     * @throws CloneNotSupportedException
     */
    @Test
    public void test_shallowCopy() throws CloneNotSupportedException {
        User user = new User("xxx", new Address("广东省", "深圳市", "南山区"));
        User user2 = (User) user.clone();
        user2.getAddress().setCity("武汉市");
        System.out.println("user address is " + user.getAddress());
        System.out.println("user2 address is " + user2.getAddress());
    }

    /**
     * 深拷贝  被复制对象的所有变量都含有与原来的对象相同的值,但对象的引用会指向被复制过的新对象
     * @throws CloneNotSupportedException
     */
    @Test
    public void test_deepCopy() throws CloneNotSupportedException {
        User2 user = new User2("xxx", new Address2("广东省", "深圳市", "南山区"));
        User2 user2 = (User2) user.clone();
        user2.getAddress().setCity("武汉市");
        System.out.println("user address is " + user.getAddress());
        System.out.println("user2 address is " + user2.getAddress());
    }



    class User implements Cloneable {
        private String name;

        private Address address;

        public User() {
        }

        public User(String name, Address address) {
            this.name = name;
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    class Address implements Cloneable {
        private String province;

        private String city;

        private String region;

        public Address() {
        }

        public Address(String province, String city, String region) {
            this.province = province;
            this.city = city;
            this.region = region;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        @Override
        public String toString() {
            return "Address{" +
                    "province='" + province + '\'' +
                    ", city='" + city + '\'' +
                    ", region='" + region + '\'' +
                    '}';
        }
    }

    class User2 implements Cloneable {
        private String name;

        private Address2 address;

        public User2() {
        }

        public User2(String name, Address2 address) {
            this.name = name;
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Address2 getAddress() {
            return address;
        }

        public void setAddress(Address2 address) {
            this.address = address;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            User2 user2 = (User2) super.clone();
            user2.address = (Address2) address.clone();
            return user2;
        }
    }

    class Address2 implements Cloneable {
        private String province;

        private String city;

        private String region;

        public Address2() {
        }

        public Address2(String province, String city, String region) {
            this.province = province;
            this.city = city;
            this.region = region;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        @Override
        public String toString() {
            return "Address{" +
                    "province='" + province + '\'' +
                    ", city='" + city + '\'' +
                    ", region='" + region + '\'' +
                    '}';
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
}
