package review.groovy

import review.java.User

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/7/27 13:45
 */

def sayHello(name) {
    User user = new User(name)
    println("hello: " + name)
    println("user hashcode: " + user.hashCode())
    return "123312"
}


sayHello(name)



//Closure.main()