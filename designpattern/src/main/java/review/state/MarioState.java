package review.state;

/**
 * 马里奥状态
 * @author: xiaoxiaoxiang
 * @date: 2020/7/21 11:37
 */
public enum MarioState {

    /**
     * 小马里奥
     */
    SMALL(0),

    /**
     * 超级马里奥
     */
    SUPER(1),

    /**
     * 火焰马里奥
     */
    FIRE(2),

    /**
     * 斗篷马里奥
     */
    CAPE(3);

    private int value;

    private MarioState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
