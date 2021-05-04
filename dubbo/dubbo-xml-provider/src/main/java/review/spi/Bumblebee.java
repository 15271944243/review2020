package review.spi;

import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/5/3 16:10
 */
@Slf4j
public class Bumblebee implements Robot {

    private BumblebeeSay bumblebeeSay;

    // 通过 setter 注入 BumblebeeSay
    public void setBumblebeeSay(BumblebeeSay bumblebeeSay) {
        this.bumblebeeSay = bumblebeeSay;
    }

    @Override
    public void sayHello() {
        // TODO Dubbo 依赖注入未完成,还缺一个 URL
        bumblebeeSay.say("Hello, I am Bumblebee.");
    }
}
