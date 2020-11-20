package review.objectlayout;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/11/19 18:00
 */
public class User {

    private String name;

    private Integer age;

    private boolean sex;


    public User() {
    }

    public User(String name, Integer age, boolean sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }
}
