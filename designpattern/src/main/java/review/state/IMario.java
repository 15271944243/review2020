package review.state;

/**
 * 马里奥状态接口
 * @author: xiaoxiaoxiang
 * @date: 2020/7/21 11:34
 */
public interface IMario {

    /**
     * 吃蘑菇
     */
    void obtainMushRoom(MarioStateMachine stateMachine);

    /**
     * 获得斗篷
     */
    void obtainCape(MarioStateMachine stateMachine);

    /**
     * 获得火焰
     */
    void obtainFireFlower(MarioStateMachine stateMachine);

    /**
     * 遇到怪物
     */
    void meetMonster(MarioStateMachine stateMachine);

    /**
     * 获取当前状态
     * @return
     */
    MarioState getState();
}
