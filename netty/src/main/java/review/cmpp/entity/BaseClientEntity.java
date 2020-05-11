package review.cmpp.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.nio.charset.Charset;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/5/9 11:23
 */
@ToString
@Getter
@Setter
public class BaseClientEntity implements Serializable {
    private static final long serialVersionUID = -8795935280886286906L;

    private Charset chartset = Charset.forName("UTF-8");
}
