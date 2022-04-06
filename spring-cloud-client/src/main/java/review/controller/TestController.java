package review.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import review.service.HelloService;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2022/4/5 14:25
 */
@Slf4j
@RestController
@RequestMapping("/sc-test")
public class TestController {

    @Autowired
    private HelloService helloService;

    @RequestMapping("/test01")
    public String scTest01(@RequestParam(name="name") String name) {
        // 基于 Spring AOP，所以不能在同一个类中调用
        return helloService.hello01(name);
    }

    @RequestMapping("/test02")
    public String scTest02(@RequestParam(name="name") String name) {
        // 基于 Spring AOP，所以不能在同一个类中调用
        return helloService.hello02(name);
    }

}
