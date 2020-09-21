package review.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/8/4 10:45
 */
@RestController
@RequestMapping("/test")
public class SpringRedisSessionController {

    /**
     * Debug SpringSessionDataRedis
     * 1. 开启@EnableRedisHttpSession
     * 2. 在{@link org.springframework.session.web.http.SessionRepositoryFilter}里debug
     * 3. 阅读{@link org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration}
     * 查看关于SessionRepositoryFilter的配置
     *
     * 可参考: https://blog.csdn.net/u010648555/article/details/79491988
     * @return
     */
    @GetMapping("/springsession")
    public String testSpringSessionDataRedis(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        String paramName = "userId";
        String userId = (String) session.getAttribute(paramName);
        if (userId == null) {
            userId = "xiaoxiang";
            session.setAttribute(paramName, userId);
        }
        return "springsession:userId:" + userId;
    }
}
