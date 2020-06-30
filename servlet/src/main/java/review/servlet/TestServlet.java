package review.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/6/30 09:57
 */
public class TestServlet extends HttpServlet {

    private static final long serialVersionUID = -278295775319306232L;

    private static final String PARAM_NAME_ONE = "param1";

    private String param1;

    @Override
    public void init() throws ServletException {
        param1 = getInitParameter(PARAM_NAME_ONE);
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("ServletRequest");
        super.service(req, res);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HttpServletRequest");
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet");
        System.out.println("param1=" + param1);

        resp.setContentType("text/html; charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.println("IP地址为：" + req.getRemoteHost() + "<br>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost");
        System.out.println("param1=" + param1);
        super.doPost(req, resp);
    }
}
