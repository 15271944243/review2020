package review.state;

/**
 * 马里奥状态机
 * @author: xiaoxiaoxiang
 * @date: 2020/7/21 11:44
 */
public class MarioStateMachine {

    private int score;

    private IMario currentState;

    public MarioStateMachine() {
        this.score = 0;
        this.currentState = SmallMario.getInstance();
    }

    /**
     * 吃蘑菇
     */
    public void obtainMushRoom() {
        this.currentState.obtainMushRoom(this);
    }

    /**
     * 获得斗篷
     */
    public void obtainCape() {
        this.currentState.obtainCape(this);
    }

    /**
     * 获得火焰
     */
    public void obtainFireFlower() {
        this.currentState.obtainFireFlower(this);
    }

    /**
     * 获取当前状态
     * @return
     */
    public String getCurrentStateName() {
        return this.currentState.getState().name();
    }

    /**
     * 遇到怪物
     */
    public void meetMonster() {
        this.currentState.meetMonster(this);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setCurrentState(IMario currentState) {
        this.currentState = currentState;
    }

    public int getScore() {
        return score;
    }

    public IMario getCurrentState() {
        return currentState;
    }
}
