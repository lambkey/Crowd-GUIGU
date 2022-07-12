package lamb.key.spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JoinYang
 * @date 2022/5/13 20:15
 */
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String testSpringBoot(){
        return "hello";
    }
}
