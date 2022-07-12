package lamb.key.spring.cloud.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author JoinYang
 * @date 2022/5/19 17:01
 */

@EnableEurekaClient
@SpringBootApplication
public class ConsumeMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumeMainApplication.class,args);
    }
}
