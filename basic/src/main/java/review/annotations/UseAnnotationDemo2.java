package review.annotations;

import java.lang.reflect.Method;

/**
 * 学习注解-使用注解Demo
 * @author: xiaoxiaoxiang
 * @date: 2020/11/18 15:10
 */
public class UseAnnotationDemo2 {

    public static void main(String[] args) {
        UseAnnotationDemo2 demo = new UseAnnotationDemo2();
        demo.getAnnotationByReflect();
    }

    /**
     * 通过反射获取注解
     */
    public void getAnnotationByReflect() {
        Class clazz = UseAnnotationDemo.class;
        for (Method method : clazz.getMethods()) {
            Todo todoAnnotation = method.getAnnotation(Todo.class);
            if (todoAnnotation != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("method name: ").append(method.getName()).append(", ")
                        .append("author: ").append(todoAnnotation.author()).append(", ")
                        .append("priority: ").append(todoAnnotation.priority()).append(", ")
                        .append("status: ").append(todoAnnotation.status());
                System.out.println(sb.toString());
            }
        }
    }
}
