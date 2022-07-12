package lamb.key.spring.cloud.provider.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lamb.key.spring.cloud.entity.Employee;
import lamb.key.spring.cloud.utils.JSONResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JoinYang
 * @date 2022/5/23 21:14
 *
 * 熔断、降级
 */

@RequestMapping("/Hystrix")
@RestController
public class ProviderHystrixController {

    // 熔断后执行的方法getEmployeeHystrix，返回的类型一一致
    @HystrixCommand(fallbackMethod = "getEmployeeHystrix")
    @RequestMapping("/getEmployee")
    public JSONResult<Employee> getEmployee(@RequestParam("signal") String signal) throws InterruptedException {
        if ("bang-little".equals(signal)){
            throw new RuntimeException();
        }
        if ("bang-more".equals(signal)){

            Thread.sleep(10000);

        }
        return JSONResult.successNeedData(new Employee(18,"老刘",666.666));
    }

    public JSONResult<Employee> getEmployeeHystrix(@RequestParam("signal") String signal){
        String message="服务出现问题，方法执行断路bang-more"+signal;
        return JSONResult.FailureNeedMessage(message);
    }


}
