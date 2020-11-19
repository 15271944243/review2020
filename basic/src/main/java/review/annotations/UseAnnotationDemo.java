package review.annotations;

/**
 * 学习注解-使用注解Demo
 * @author: xiaoxiaoxiang
 * @date: 2020/11/18 15:08
 */
public class UseAnnotationDemo {

    /**
     * 此方法不添加注解
     */
    public void method01() {

    }

    /**
     * 给方法添加注解
     */
    @Todo(author = "xiaoxiaoxiang", priority = Todo.Priority.HIGH, status = Todo.Status.NOT_STARTED)
    public void importantMethod() {

    }

    /**
     * 给方法添加注解
     */
    @Todo(author = "xiaoxiaoxiang", priority = Todo.Priority.LOW, status = Todo.Status.NOT_STARTED)
    public void unimportantMethod() {

    }


}
