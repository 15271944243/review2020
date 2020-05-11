package review.cmpp.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/5/9 10:33
 */
@ToString(callSuper = true)
@Getter
@Setter
public class CmppClientEntity extends BaseClientEntity {

    private static final long serialVersionUID = 337051931356368266L;

    /**
     * 登录用户名
     */
    private String userName;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 协议版本
     */
    private short version = (short) 0x20L;

}
