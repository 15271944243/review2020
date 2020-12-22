package review.jksj.common;

import lombok.Data;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/12/21 15:14
 */
@Data
public class MessageHeader {

    /**
     * 版本号
     */
    private int version = 1;

    /**
     * 唯一标志符
     */
    private long streamId;

    /**
     * 操作码
     */
    private int opCode;
}
