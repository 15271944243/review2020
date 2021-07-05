package review.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/5/19 15:44
 */
public class ReflectionDemo {

    public static void main(String[] args) {
//        getClazz();
//        newInstance();
//        setFieldValue();
        invokeMethod();
    }

    /**
     * 获取 Class 对象
     */
    private static void getClazz() {
        // 1. 通过对象实例的 getClass() 方法获取Class对象
        Student student = new Student();
        Class<? extends Student> studentClass1 = student.getClass();
        // 2. 通过类.class 获取Class对象
        Class<? extends Student> studentClass2 = Student.class;
        // 3. 通过 Class.forName("类的全路径名")
        try {
            // 通常使用这一种
            Class studentClass3 = Class.forName("review.reflection.Student");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过构造方法创建对象实例
     */
    private static void newInstance() {
        Class studentClazz = null;
        try {
            studentClazz = Class.forName("review.reflection.Student");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 获取全部 public 构造方法
        Constructor[] conArray = studentClazz.getConstructors();
        // 获取全部 public、default、protected 构造方法
        Constructor[] conArray2 = studentClazz.getDeclaredConstructors();

        try {
            // 获取无参构造方法
            Constructor con1 = studentClazz.getConstructor();
            // 创建对象实例
            Student stu1 = (Student) con1.newInstance();
            // 获取私有的,有参构造方法
            Constructor con2 = studentClazz.getDeclaredConstructor(String.class, int.class);
            con2.setAccessible(true);
            Student stu2 = (Student) con2.newInstance("xiaoxiang", 29);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置属性值
     */
    private static void setFieldValue() {
        try {
            Class studentClazz = Class.forName("review.reflection.Student");
            Constructor con = studentClazz.getConstructor(String.class, Integer.class, int.class, String.class);
            Student stu = (Student) con.newInstance("xxx", 1, 10, "三年二班");
            // 获取所有 public 属性
            Field[] fieldArray = studentClazz.getFields();
            // 获取全部 public、default、protected 属性
            Field[] fieldArray2 = studentClazz.getDeclaredFields();
            // 获取单个 public 属性
            Field f1 = studentClazz.getField("name");
            // 获取单个 public、default、protected 属性
            Field f2 = studentClazz.getDeclaredField("age");
            f2.setAccessible(true);
            // 给 name 属性设值
            f1.set(stu, "xiaoxiang");
            // 给 age 属性设值
            f2.set(stu, 11);
            System.out.println(stu.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 方法调用
     */
    private static void invokeMethod() {
        try {
            Class studentClazz = Class.forName("review.reflection.Student");
            Constructor con = studentClazz.getConstructor(String.class, Integer.class, int.class, String.class);
            Student stu = (Student) con.newInstance("xxx", 1, 10, "三年二班");
            // 获取所有 public 方法
            Method[] methodArray = studentClazz.getMethods();
            // 获取全部 public、default、protected 方法
            Method[] methodArray2 = studentClazz.getDeclaredMethods();
            // 获取单个 public 方法
            Method m1 = studentClazz.getMethod("setAge", int.class);
            m1.invoke(stu, 12);
            // 获取单个 public、default、protected 方法
            Method m2 = studentClazz.getDeclaredMethod("hello");
            m2.setAccessible(true);
            m2.invoke(stu);
            System.out.println(stu.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
