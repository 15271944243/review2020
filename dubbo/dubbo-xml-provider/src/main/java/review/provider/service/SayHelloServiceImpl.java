package review.provider.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import review.api.service.SayHelloService;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/1/18 15:10
 */
@Slf4j
@Service("sayHelloService")
public class SayHelloServiceImpl implements SayHelloService {

    @Override
    public String sayHello(String name) {
        return "hello " + name + " !";
    }
}
