package lamb.key.save.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author JoinYang
 * @date 2022/6/6 16:42
 * @Version 1.0
 * session服务共享，不同的服务可以使用不同的业务场景
 */
@RestController
public class RedisSetController {

    @RequestMapping("/save/redis/session")
    public String save(HttpSession httpSession){
        httpSession.setAttribute("username","Hello");
        return "session存入redis中";
    }
}
