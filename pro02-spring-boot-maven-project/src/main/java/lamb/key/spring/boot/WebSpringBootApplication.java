package lamb.key.spring.boot;

import lamb.key.spring.boot.entity.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author JoinYang
 * @date 2022/5/13 20:09
 */
@SpringBootApplication
@ComponentScan("lamb.**.controller")
// 启动properties配置类，指定启用的配置类
@EnableConfigurationProperties({Student.class})
public class WebSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebSpringBootApplication.class,args);
    }
}
