package review.spi;

import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/5/3 19:34
 */
@Slf4j
public class OptimusPrimeSay implements RobotSay{

    @Override
    public void say(String word) {
        log.info("Optimus Prime Say: {}", word);
    }
}

