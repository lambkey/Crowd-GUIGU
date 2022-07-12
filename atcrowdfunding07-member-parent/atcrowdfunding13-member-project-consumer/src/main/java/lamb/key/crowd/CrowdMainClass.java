package lamb.key.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author JoinYang
 * @date 2022/5/28 18:18
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients("lamb.key")
@ComponentScan("lamb.key")
public class CrowdMainClass {
    public static void main(String[] args) {
        SpringApplication.run(CrowdMainClass.class,args);
    }
}
