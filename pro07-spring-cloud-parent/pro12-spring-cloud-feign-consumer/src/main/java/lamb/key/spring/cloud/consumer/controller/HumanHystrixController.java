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
 * @date 2022/5/25 18:59
 */
@RestController
@RequestMapping("/Hystrix")
public class HumanHystrixController {

    @Autowired
    private EmpRemoteService remoteService;

    @RequestMapping("/InProvider/getEmployee")
    public JSONResult<Employee> hystrixGetEmployee(@RequestParam("signal") String signal){
        return remoteService.getEmployee(signal);
    }

    @RequestMapping("/test/fallBack")
    public JSONResult testFallback(@RequestParam("signal") String signal){
        return remoteService.getEmployee(signal);
    }
}
