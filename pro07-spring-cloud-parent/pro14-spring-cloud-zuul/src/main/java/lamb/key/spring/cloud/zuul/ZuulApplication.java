package lamb.key.spring.cloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author JoinYang
 * @date 2022/5/26 8:50
 */
@SpringBootApplication
// 使用zuul网关代理功能
@EnableZuulProxy
@ComponentScan("lamb.key")
public class ZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class,args);
    }
}
