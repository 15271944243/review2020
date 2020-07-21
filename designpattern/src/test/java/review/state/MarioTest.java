package review.state;

import org.junit.Test;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/7/21 14:27
 */
public class MarioTest {

    @Test
    public void test() {
        MarioStateMachine stateMachine = new MarioStateMachine();
        stateMachine.obtainMushRoom();
        System.out.println("state: " + stateMachine.getCurrentStateName());
        System.out.println("score: " + stateMachine.getScore());

        stateMachine.obtainFireFlower();

        System.out.println("state: " + stateMachine.getCurrentStateName());
        System.out.println("score: " + stateMachine.getScore());

        stateMachine.obtainCape();

        System.out.println("state: " + stateMachine.getCurrentStateName());
        System.out.println("score: " + stateMachine.getScore());

        stateMachine.meetMonster();

        System.out.println("state: " + stateMachine.getCurrentStateName());
        System.out.println("score: " + stateMachine.getScore());
    }
}
