package lamb.key.spring.cloud.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author JoinYang
 * @date 2022/5/19 17:01
 */

// 注册Eureka，使用Eureka
@EnableEurekaClient
// 使用Feign客户端功能
@EnableFeignClients("lamb.key")
@ComponentScan("lamb.key")
@SpringBootApplication
public class ConsumeMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumeMainApplication.class,args);
    }
}
