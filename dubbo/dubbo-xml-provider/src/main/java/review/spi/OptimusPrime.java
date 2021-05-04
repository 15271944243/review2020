package review.spi;

import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/5/3 16:10
 */
@Slf4j
public class OptimusPrime implements Robot {

    private OptimusPrimeSay optimusPrimeSay;

    // 通过 setter 注入 OptimusPrimeSay
    public void setOptimusPrimeSay(OptimusPrimeSay optimusPrimeSay) {
        this.optimusPrimeSay = optimusPrimeSay;
    }

    @Override
    public void sayHello() {
        // TODO Dubbo 依赖注入未完成,还缺一个 URL
        optimusPrimeSay.say("Hello, I am Optimus Prime.");
    }
}
