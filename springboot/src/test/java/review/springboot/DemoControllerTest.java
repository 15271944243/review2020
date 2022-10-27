package review.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import review.springboot.vo.DemoReqVO;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2022/10/27 15:06
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SbApplication.class)
public class DemoControllerTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testValidated() {
        String url = "http://127.0.0.1/demo/validated";
        DemoReqVO reqVO = new DemoReqVO();
        ResponseEntity<String> objectResponseEntity = restTemplate.postForEntity(url, reqVO, String.class);
    }

    @Test
    public void testValid() {
        String url = "http://127.0.0.1/demo/valid";
        DemoReqVO reqVO = new DemoReqVO();
        ResponseEntity<String> objectResponseEntity = restTemplate.postForEntity(url, reqVO, String.class);
    }
}
