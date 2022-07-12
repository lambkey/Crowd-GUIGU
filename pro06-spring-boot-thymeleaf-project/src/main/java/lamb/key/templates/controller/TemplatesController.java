package lamb.key.templates.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

/**
 * @author JoinYang
 * @date 2022/5/18 0:09
 */
@Controller
public class TemplatesController {

    @Autowired
    private ServletContext servletContext;

    /*
    *  1、文本替换测试
    * */
    @RequestMapping("/test/templates01")
    public String testTemplates01(){
        return "test";
    }

    /*
    *  2、访问各个属性域
    * */
    @RequestMapping("/test/templates02")
    public String testTemplates02(ModelMap modelMap, HttpSession httpSession){

        modelMap.addAttribute("attrNameRequestScope","attrValueRequestScope");
        httpSession.setAttribute("attrNameSessionScope","attrValuesSessionScope");
        servletContext.setAttribute("attrNameContextScope","attrValueContextScope");
        return "test";
    }

    /*
    *  3、template遍历集合
    * */

    @RequestMapping("/test/templates03")
    public String testTemplate03(ModelMap modelMap){
        modelMap.addAttribute("list", Arrays.asList(
                "aa","bbb","ccc"
        ));

        return "test";
    }


}
