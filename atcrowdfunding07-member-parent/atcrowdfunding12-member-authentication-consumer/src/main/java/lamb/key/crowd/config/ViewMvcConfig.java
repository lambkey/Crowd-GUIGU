package lamb.key.crowd.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author JoinYang
 * @date 2022/6/2 1:11
 */

// 创建view controller
@Configuration
public class ViewMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/registry/page.html").setViewName("registry-page");
        registry.addViewController("/page/to/login.html").setViewName("login");
        registry.addViewController("/auth/success/to/member/main").setViewName("member");
        registry.addViewController("/trans/to/my/crowd").setViewName("my-crowd");
    }
}
