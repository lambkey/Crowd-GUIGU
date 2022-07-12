package lamb.key.spring.cloud.provider.controller;


import lamb.key.spring.cloud.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author JoinYang
 * @date 2022/5/19 16:42
 */
@RestController
public class ProviderMainController {
    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping("/provide/employee")
    public Employee provideEmployee(HttpServletRequest request){
        int serverPort = request.getServerPort();
        return new Employee(52,"张三"+serverPort,168.00);
    }

    @RequestMapping("/provide/feign/employee")
    public Employee getEmployeeById(@RequestParam("empId") Integer empId){
        int port = httpServletRequest.getServerPort();
        return new Employee(empId,"employee"+port,100.00);
    }
}
