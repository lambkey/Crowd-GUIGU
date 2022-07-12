package lamb.key.mybatis;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author JoinYang
 * @date 2022/5/17 12:27
 */


@MapperScan("lamb.**.mapper")
@ComponentScan(basePackages = {"lamb.key"})
@SpringBootApplication
public class WebSpringBootApplicationDemo {
    public static void main(String[] args) {
        SpringApplication.run(WebSpringBootApplicationDemo.class,args);
    }
}
