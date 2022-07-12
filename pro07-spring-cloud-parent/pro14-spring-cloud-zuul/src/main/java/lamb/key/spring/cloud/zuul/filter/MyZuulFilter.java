package lamb.key.spring.cloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author JoinYang
 * @date 2022/5/26 9:30
 */

@Component
public class MyZuulFilter extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(MyZuulFilter.class);
    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();

        HttpServletRequest request = requestContext.getRequest();

        String requestZuul=request.getParameter("signal");

        return "hello".equals(requestZuul);
    }

    @Override
    public Object run() throws ZuulException {

        logger.info("signal请求参数值等于hello，执行过滤");
        return null;
    }

    @Override
    public String filterType() {
        String pre="pre";
        return pre;
    }

    @Override
    public int filterOrder() {
        return 0;
    }
}
