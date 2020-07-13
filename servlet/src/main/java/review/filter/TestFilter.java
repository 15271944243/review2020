package review.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
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

        ResponseWrapper wrapperResponse = new ResponseWrapper((HttpServletResponse) response);
        chain.doFilter(request, wrapperResponse);


//        ServletOutputStream out = response.getOutputStream();
//        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(out);
//        bufferedOutputStream.flush();


//        ServletOutputStream out = response.getOutputStream();

        byte[] content = wrapperResponse.getContent();
        //判断是否有值
        if (content.length > 0) {
            String str = new String(content, "UTF-8");
            System.out.println("返回值:" + str);
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
            //把返回值输出到客户端
            ServletOutputStream out = response.getOutputStream();
            out.write(str.getBytes());
            out.flush();
            out.close();
        }
        System.out.println("-------outbound-------");
    }

    @Override
    public void destroy() {

    }
}
