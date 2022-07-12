package lamb.key.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author JoinYang
 * @date 2022/7/2 23:23
 * @Version 1.0
 */
@SpringBootApplication
@ComponentScan("lamb.key")
@EnableEurekaClient
@EnableFeignClients("lamb.key")
public class CrowdMainClass {
    public static void main(String[] args) {
        SpringApplication.run(CrowdMainClass.class,args);
    }
}
