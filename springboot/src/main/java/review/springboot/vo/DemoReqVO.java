package review.springboot.vo;

import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2022/10/27 14:37
 */
@ToString
public class DemoReqVO implements Serializable {

    private static final long serialVersionUID = 3791219834231255817L;
    @NotBlank
    private String userId;

}
