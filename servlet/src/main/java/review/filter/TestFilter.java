package review.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/6/30 09:57
 */
public class TestFilter implements Filter {

    private static final String PARAM_NAME_ONE = "param1";

    private String param1;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        param1 = filterConfig.getInitParameter(PARAM_NAME_ONE);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("-------inbound-------");
        System.out.println("param1=" + param1);
        chain.doFilter(request, response);
        System.out.println("-------outbound-------");
    }

    @Override
    public void destroy() {

    }
}
