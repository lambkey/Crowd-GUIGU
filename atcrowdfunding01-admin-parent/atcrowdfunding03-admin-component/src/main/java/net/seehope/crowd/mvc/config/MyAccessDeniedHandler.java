package net.seehope.crowd.mvc.config;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author JoinYang
 * @date 2022/5/9 20:14
 */
// 设置security无权访问异常处理显示，403
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        //1 设置响应状态码 >这里设置403
        response.sendRedirect(request.getContextPath()+"/noAuth/to/error/page.html");
    }
}

