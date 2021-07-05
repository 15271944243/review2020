package review.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Document(indexName = "snowplow-*", type = "", shards = 3)
public class Snowplow implements Serializable {

    private static final long serialVersionUID = 5642980337929312238L;
    /**
     * topic id
     */
//    @JsonProperty("event_version")
    private String event_version;

    private String event_format;

    /**
     * 用户访问记录
     */
    @Field(type = FieldType.Nested)
    private SnowplowVisitData unstruct_event_com_xhqb_track_1;

    public String getEvent_version() {
        return event_version;
    }

    public void setEvent_version(String event_version) {
        this.event_version = event_version;
    }

    public String getEvent_format() {
        return event_format;
    }

    public void setEvent_format(String event_format) {
        this.event_format = event_format;
    }

    public SnowplowVisitData getUnstruct_event_com_xhqb_track_1() {
        return unstruct_event_com_xhqb_track_1;
    }

    public void setUnstruct_event_com_xhqb_track_1(SnowplowVisitData unstruct_event_com_xhqb_track_1) {
        this.unstruct_event_com_xhqb_track_1 = unstruct_event_com_xhqb_track_1;
    }
}
