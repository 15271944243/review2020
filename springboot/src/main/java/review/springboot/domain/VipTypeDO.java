package review.springboot.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class VipTypeDO implements Serializable {
    private static final long serialVersionUID = 4991051955919109530L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 会员类型
     */
    private String code;
    /**
     *  会员名称
     */
    private String name;
    /**
     * 名称多语言编码
     */
    private String languageCode;
    /**
     *  临时会员:0表示非临时会员；1：表示临时会员
     */
    private Integer temp;
    /**
     *  会员权益列表:会员权益列表，以逗号隔开
     */
    private String rightsCodes;
    /**
     * 会员消耗优先级
     */
    private Integer level;
    /**
     *  国家
     */
    private String country;
    /**
     * 品牌
     */
    private String brand;
    /**
     * 销售渠道:online:表示线上；offline：
     * 表示线下
     */
    private String saleChannel;
    /**
     *  会员铭牌图片
     */
    private String iconPath;
    /**
     * 会员灰态铭牌图片
     */
    private String grayIconPath;

    /**
     * 头像样式图片
     */
    private String avatarStyle;
    /**
     *  创建时间
     */
    private Date createTime;
    /**
     *  记录更新时间
     */
    private Date updateTime;
    /**
     * 创建人id
     */
    private String creatorId;
    /**
     * 最后更新人ID
     */
    private String lastModifierId;
}