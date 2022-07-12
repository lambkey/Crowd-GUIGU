package net.seehope.crowd.constant;

import java.util.HashSet;
import java.util.Set;

/**
 * @author JoinYang
 * @date 2022/6/6 22:50
 * @Version 1.0
 * zuul过滤器，过滤静态资源，可访问资源
 */
public class AccessPassResources {
    public static final Set<String> PASS_RES_SET = new HashSet<>();

    // 类加载时会执行静态代码块zuulFilter不需要拦截的资源
    static {
        /*开始访问页面*/
        PASS_RES_SET.add("/");

        /*登录页面页面*/
        PASS_RES_SET.add("/page/to/login.html");
        /*登录提交表单*/
        PASS_RES_SET.add("/member/do/login");

        /*注册页面*/
        PASS_RES_SET.add("/registry/page.html");
        /*注册提交表单*/
        PASS_RES_SET.add("/registry/save/info");

        /*发送验证码*/
        PASS_RES_SET.add("/registry/send/sms");
        /*t退出登录*/
        PASS_RES_SET.add("/member/do/logout");

    }

    public static final Set<String> STATIC_RES_SET = new HashSet<>();
    static {
                STATIC_RES_SET.add("bootstrap");
                STATIC_RES_SET.add("css");
                STATIC_RES_SET.add("fonts");
                STATIC_RES_SET.add("img");
                STATIC_RES_SET.add("jquery");
                STATIC_RES_SET.add("layer");
                STATIC_RES_SET.add("script");
                STATIC_RES_SET.add("ztree");

    }
    // 目标：传进来的是contextPath()，例如/css/bb/cc,识别css是否为resource下static下面的静态资源，如果是就放行
    public static boolean judgeCurrentServletPathWhetherStaticResource(String serverPath){

        if (serverPath==null||serverPath.length()==0){
            return true;
        }

        // 假设传入的是/css/bbb/ccc,会把它们分成一个数组["","css","bbb","ccc"]
        String []strings = serverPath.split("/");
        String goal = strings[1];

        return STATIC_RES_SET.contains(goal);
    }
}
