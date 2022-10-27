package review.springboot;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import review.springboot.utils.OkHttpClientUtil;
import review.springboot.vo.DemoReqVO;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2022/10/27 15:06
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SbApplication.class)
public class DemoControllerTest {




    @Test
    public void testValidated() {
        String url = "http://127.0.0.1:8080/demo/validated";
        DemoReqVO reqVO = new DemoReqVO();
        String result = OkHttpClientUtil.post(url, JSON.toJSONString(reqVO));
    }

    @Test
    public void testValid() {
        String url = "http://127.0.0.1:8080/demo/valid";
        DemoReqVO reqVO = new DemoReqVO();
        String result = OkHttpClientUtil.post(url, JSON.toJSONString(reqVO));
    }
}
