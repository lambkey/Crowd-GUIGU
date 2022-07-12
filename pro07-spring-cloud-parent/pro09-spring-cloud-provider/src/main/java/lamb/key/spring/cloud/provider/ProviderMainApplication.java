package lamb.key.spring.cloud.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author JoinYang
 * @date 2022/5/19 16:40
 */
//  使用@EnableCircuitBreaker注解开启断路器功能(熔断)
@EnableCircuitBreaker
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class ProviderMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderMainApplication.class,args);
    }
}
