package lamb.key.spring.cloud.consumer.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author JoinYang
 * @date 2022/5/19 17:04
 */
@Configuration
public class ConsumeCloudConfig {
    @Bean
    // 这个注解让RestTemplate有负载均衡功能，通过Ribbon访问Provider集群
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
