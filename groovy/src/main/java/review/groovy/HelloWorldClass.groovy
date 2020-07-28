package review.groovy

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/7/27 17:19
 */
class HelloWorldClass {

    String sayHello(String name, String sex, int age) {
        println 'hello world class name: ' + name + ', sex: ' + sex + ', age: ' + age
        return "name: " + name + ", sex: " + sex + ", age: " + age
    }
}