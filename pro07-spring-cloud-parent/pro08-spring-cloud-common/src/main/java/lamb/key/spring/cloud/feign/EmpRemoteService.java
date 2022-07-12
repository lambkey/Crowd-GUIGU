package lamb.key.spring.cloud.feign;

import lamb.key.spring.cloud.entity.Employee;
import lamb.key.spring.cloud.factory.MyFallBackFactory;
import lamb.key.spring.cloud.utils.JSONResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author JoinYang
 * @date 2022/5/21 0:09
 */

@FeignClient(value = "lambkey-provider",fallbackFactory = MyFallBackFactory.class)
public interface EmpRemoteService {

    @RequestMapping("/Hystrix/getEmployee")
    public JSONResult<Employee> getEmployee(@RequestParam("signal") String signal);

    @RequestMapping("/provide/feign/employee")
    public Employee getEmployeeById(@RequestParam("empId") Integer empId);
}
