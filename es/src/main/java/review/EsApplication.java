package review;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import review.pojo.Snowplow;

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
        test();
    }

    private void test() {
        String firstAccessTime = null;
        BoolQueryBuilder boolQueryBuilder = boolQuery();
        boolQueryBuilder.must(termQuery("app_id", "mobile.xhqb.com"));
        boolQueryBuilder.must(termQuery("event_name", "track"));
        boolQueryBuilder.must(termQuery("unstruct_event_com_xhqb_track_1.category", "wjApplyActivity"));
        //boolQueryBuilder.must(termQuery("unstruct_event_com_xhqb_track_1.action", "visit"));
        boolQueryBuilder.must(termQuery("unstruct_event_com_xhqb_track_1.label", "20191218000003910010"));
        SortBuilder sortBuilder = SortBuilders.fieldSort("collector_tstamp").order(SortOrder.ASC);
        NativeSearchQueryBuilder searchQuery = new NativeSearchQueryBuilder();
        searchQuery.withQuery(boolQueryBuilder)//.withIndices("snowplow").withTypes("enriched")
                .withSort(sortBuilder)
                .withPageable(PageRequest.of(0, 10));
        IndexCoordinates index = IndexCoordinates.of("snowplow");
        SearchHits<Snowplow> snowplowSearchHits = elasticsearchOperations.search(searchQuery.build(), Snowplow.class, index);
        List<SearchHit<Snowplow>> searchHitList = snowplowSearchHits.getSearchHits();
        log.info("cid:{}埋点查询结果{}", "20191218000003910010", searchHitList.size());
    }
}
