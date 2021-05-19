package review.reflection;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/5/19 15:41
 */
public class Student {

    public String name;

    private Integer sex;

    private int age;

    /**
     * 班级名称
     */
    private String className;

    public Student() {
    }

    private Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student(String name, Integer sex, int age, String className) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    private void hello () {
        System.out.println("hello, student: " + this.name);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", className='" + className + '\'' +
                '}';
    }
}
