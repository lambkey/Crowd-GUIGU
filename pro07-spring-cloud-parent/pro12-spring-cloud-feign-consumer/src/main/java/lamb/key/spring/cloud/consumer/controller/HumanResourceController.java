package lamb.key.spring.cloud.consumer.controller;

import lamb.key.spring.cloud.entity.Employee;
import lamb.key.spring.cloud.feign.EmpRemoteService;
import lamb.key.spring.cloud.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JoinYang
 * @date 2022/5/19 17:06
 */
@RestController
@RequestMapping("/consume")
public class HumanResourceController {
    // Feign提供访问provider的接口
    @Autowired
    private EmpRemoteService empRemoteService;

    @RequestMapping("/feign/getEmployee")
    public Employee getEmployeeByFeign(){
        return empRemoteService.getEmployeeById(1);
    }

    @RequestMapping("/test/fallBack")
    public JSONResult testFallback(@RequestParam("signal") String signal){
        return empRemoteService.getEmployee(signal);
    }
}
