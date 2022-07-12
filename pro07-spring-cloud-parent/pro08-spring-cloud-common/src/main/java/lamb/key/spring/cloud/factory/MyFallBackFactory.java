package lamb.key.spring.cloud.factory;

import feign.hystrix.FallbackFactory;
import lamb.key.spring.cloud.entity.Employee;
import lamb.key.spring.cloud.feign.EmpRemoteService;
import lamb.key.spring.cloud.utils.JSONResult;
import org.springframework.stereotype.Component;

/**
 * @author JoinYang
 * @date 2022/5/25 0:45
 */
@Component
public class MyFallBackFactory implements FallbackFactory<EmpRemoteService> {

    @Override
    public EmpRemoteService create(Throwable throwable) {

        return new EmpRemoteService() {
            @Override
            public Employee getEmployeeById(Integer empId) {
                return null;
            }

            @Override
            public JSONResult<Employee> getEmployee(String signal) {
                return JSONResult.FailureNeedMessage(throwable.getMessage()+"访问provider服务出现异常，实现服务降级");
            }
        };
    }
}
