package review;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import review.pojo.Snowplow;

import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/9/8 10:45
 */
@Slf4j
@SpringBootApplication
public class EsApplication implements ApplicationListener<ApplicationReadyEvent> {

    public static void main(String[] args) {
        SpringApplication.run(EsApplication.class, args);
    }

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
//        queryApplyExitEsDate();
    }

    private void test() {
        String firstAccessTime = null;
        BoolQueryBuilder boolQueryBuilder = boolQuery();
        boolQueryBuilder.must(termQuery("app_id", "mobile.com"));
        boolQueryBuilder.must(termQuery("event_name", "track"));
        boolQueryBuilder.must(termQuery("unstruct_event_com_track_1.category", "wjApplyActivity"));
        //boolQueryBuilder.must(termQuery("unstruct_event_com__track_1.action", "visit"));
        boolQueryBuilder.must(termQuery("unstruct_event_com_track_1.label", "20191218000003910010"));
        SortBuilder sortBuilder = SortBuilders.fieldSort("collector_tstamp").order(SortOrder.ASC);
        NativeSearchQueryBuilder searchQuery = new NativeSearchQueryBuilder();
        searchQuery.withQuery(boolQueryBuilder)//.withIndices("snowplow").withTypes("enriched")
                .withSort(sortBuilder)
                .withPageable(PageRequest.of(0, 10));
//        IndexCoordinates index = IndexCoordinates.of("snowplow");
//        SearchHits<Snowplow> snowplowSearchHits = elasticsearchOperations.search(searchQuery.build(), Snowplow.class, index);
//        List<SearchHit<Snowplow>> searchHitList = snowplowSearchHits.getSearchHits();
//        log.info("cid:{}埋点查询结果{}", "20191218000003910010", searchHitList.size());
    }

    private List<Snowplow> queryApplyExitEsDate() {
        List<Snowplow> snowplowList = new ArrayList<>();
        BoolQueryBuilder boolQueryBuilder = boolQuery();
        boolQueryBuilder.must(termQuery("unstruct_event_com_app_element_click_2.code", "20019"));

//        boolQueryBuilder.must(QueryBuilders.rangeQuery("dvce_created_tstamp").gt(startTime).lt(endTime));

        //boolQueryBuilder.must(termQuery("user_id", md5Cid));
        boolQueryBuilder.must(termQuery("app_id", "com.app.android"));
        SortBuilder sortBuilder = SortBuilders.fieldSort("collector_tstamp").order(SortOrder.ASC);
        NativeSearchQueryBuilder searchQuery = new NativeSearchQueryBuilder();
        searchQuery.withQuery(boolQueryBuilder)
                .withIndices("snowplow-2020-11-19") //.withIndices("snowplow").withTypes("enriched")
                .withSort(sortBuilder)
                .withPageable(PageRequest.of(0, 200));
        snowplowList = elasticsearchOperations.queryForList(searchQuery.build(), Snowplow.class);

        return snowplowList;
    }
}
