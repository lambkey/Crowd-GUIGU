package lamb.key.get.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author JoinYang
 * @date 2022/6/6 16:46
 * @Version 1.0
 */
@RestController
public class RedisGetController {
    @RequestMapping("/get/redis/session")
    public String save(HttpSession httpSession){
        String value = (String) httpSession.getAttribute("username");
        return value;
    }
}
