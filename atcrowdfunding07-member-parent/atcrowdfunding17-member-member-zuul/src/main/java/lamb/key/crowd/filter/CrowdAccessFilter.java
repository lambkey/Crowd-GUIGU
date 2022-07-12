package lamb.key.crowd.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import net.seehope.crowd.constant.AccessPassResources;
import net.seehope.crowd.constant.CrowdConstant;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author JoinYang
 * @date 2022/6/6 23:52
 * @Version 1.0
 * zuulFilter
 */
@Component
@Slf4j
public class CrowdAccessFilter extends ZuulFilter {


    @Override
    public int filterOrder() {
        return 0;
    }


    @Override
    public boolean shouldFilter() {
        // 1.获取当前请求对象
        RequestContext currentContext = RequestContext.getCurrentContext();

        HttpServletRequest request = currentContext.getRequest();

        // 2.获取当前请求路径
        String contextPath = request.getServletPath();

        log.info(contextPath);
        boolean contains = AccessPassResources.PASS_RES_SET.contains(contextPath);
        // 返回false代表放行
        // 1.判断是否为过滤的路径
        if (contains){
            return false;
        }

        // 2.判断是否为过滤的静态资源
        return !AccessPassResources.judgeCurrentServletPathWhetherStaticResource(contextPath);

    }

    @Override
    public Object run() throws ZuulException {

        // 1.获取当前请求对象
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        // 2.获取当前session对象
        HttpSession session = request.getSession();

        // 3.尝试从session对象中获取已登录的用户
        Object attribute = session.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN_SESSION);

        // 4.判断loginMember是否为空
        if (attribute == null){
            // 5.从requestContext对象中获取Response对象
            HttpServletResponse response = requestContext.getResponse();

            // 6.将消息存入Session域
            session.setAttribute(CrowdConstant.MESSAGE_CODE_ERROR,CrowdConstant.MESSAGE_ACCESS_FORBIDEN);

            try {
                response.sendRedirect("/page/to/login.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public String filterType() {

        // 在目标为服务前过滤
        return "pre";
    }
}
