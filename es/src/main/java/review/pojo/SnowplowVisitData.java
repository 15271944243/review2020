package review.pojo;

import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2018/12/14 16:54
 */
public class SnowplowVisitData implements Serializable {

    private static final long serialVersionUID = 6309585307642406802L;
    /**
     * 种类
     */
    @Field
    private String category;
    /**
     * 行为
     */
    @Field
    private String action;
    /**
     * cid
     */
    @Field
    private String label;
    /**
     * value的意义
     */
    @Field
    private String property;
    /**
     * 值
     */
    @Field
    private String value;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
