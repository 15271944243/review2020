package review.service;

import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2022/6/1 8:05
 */
@Component
public class UserService {

    private String userName;

    public UserService() {
        System.out.println("UserService 空构造函数, this=" + this);
    }

    public UserService(String userName) {
        this.userName = userName;
        System.out.println("UserService 非空构造函数, this=" + this);
    }

    public String getUserName() {
        return userName;
    }
}
