//package review.spring;
//
//import com.smschannel.biz.RedisTemplateMocker;
//import com.smschannel.common.dal.dto.SmsInfo;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.Spy;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.data.redis.core.RedisTemplate;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
///**
// * Controller最好还是采用MockMvc的方式写测试用例(注释代码), 不要像我这样写
// * @author: xiaoxiaoxiang
// * @date: 2020/12/29 09:20
// */
////@RunWith(SpringRunner.class)
////@WebMvcTest(QueryController.class)
//@RunWith(MockitoJUnitRunner.Silent.class)
//public class QueryControllerTest {
//
//    @Mock
//    private RedisTemplate<String, Object> jsonRedisTemplate;
//
//    @Spy
//    @InjectMocks
//    private QueryController queryController;
//
//    private RedisTemplateMocker redisTemplateMocker;
//
//    @Before
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//        redisTemplateMocker = new RedisTemplateMocker();
//    }
//
//    @Test
//    public void allLengthTest() {
//        jsonRedisTemplate = redisTemplateMocker.mockJsonRedisTemplate(jsonRedisTemplate);
//        queryController.allLength();
//    }
//
//    @Test
//    public void deliverRecordTest() {
//        jsonRedisTemplate = redisTemplateMocker.mockJsonRedisTemplate(jsonRedisTemplate);
//        queryController.allLength();
//    }
//
//    @Test
//    public void smsInfoListTest() {
//        SmsInfo smsInfo = new SmsInfo();
//        List<SmsInfo> smsInfoList = new ArrayList<>();
//        smsInfoList.add(smsInfo);
//        jsonRedisTemplate = redisTemplateMocker.mockJsonRedisTemplate(jsonRedisTemplate);
//        queryController.pushRecord(smsInfoList);
//    }
//
//
////    @Autowired
////    private MockMvc mockMvc;
////
////    @Test
////    public void allLengthTest() {
////        try {
////            MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/query/length"))
////                    .andDo(MockMvcResultHandlers.print())
////                    .andExpect(MockMvcResultMatchers.status().isOk())
////                    .andReturn();
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////    }
//
//}
