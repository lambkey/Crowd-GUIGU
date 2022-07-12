package lamb.key.spring.cloud.consumer.controller;

import lamb.key.spring.cloud.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author JoinYang
 * @date 2022/5/19 17:06
 */
@RestController
@RequestMapping("/consume")
public class HumanResourceController {


    // RestTemplate用来连接远程服务获取数据
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/ribbon/employee")
    public Employee getEmployeeRemote(){
        // 1.声明远程微服务的主机地址加端口号
        /*String host = "http://localhost:1000";*/

        // 2.将远程微服务调用地址从"ip地址+端口号"改成"微服务名称"
        String host = "http://lambkey-provider";

        String url = "/provide/employee";
        return restTemplate.getForObject(host+url,Employee.class);
    }
}
