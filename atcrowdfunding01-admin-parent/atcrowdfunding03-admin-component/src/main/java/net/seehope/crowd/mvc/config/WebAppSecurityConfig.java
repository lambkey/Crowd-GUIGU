package net.seehope.crowd.mvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @author JoinYang
 * @date 2022/5/7 20:24
 * */


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService adminDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccessDeniedHandler myAccessDeniedHandler;


    @Bean
    public ReloadableResourceBundleMessageSource messageSource(){

        ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();

        reloadableResourceBundleMessageSource.setBasename("classpath:messages");

        reloadableResourceBundleMessageSource.setDefaultEncoding("UTF-8");

        return reloadableResourceBundleMessageSource;

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*auth.inMemoryAuthentication()
                .withUser("lamb")
                .password("123456")
                .roles("tom");*/
        auth.userDetailsService(adminDetailService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin/to/login/page.html","/admin/security/do/login.html",
                        "/bootstrap/**","/css/**","/fonts/**","/img/**",
                        "/jquery/**","/layer/**","/script/**","/ztree/**")
                .permitAll()


                // 用户维护模块权限设定
                // 只有PM-项目经理、OPR-项目经理角色可以访问这个资源
                .antMatchers("/admin/get/page.html")
                .hasAnyRole("PM-项目经理","OPR-项目经理")
                /*.access("hasRole('经理') OR hasAuthority ('user:get')")*/

                .anyRequest()
                .authenticated()




                .and()
                .formLogin()
                .loginPage("/admin/to/login/page.html")                 // 默认登录页面表单
                .loginProcessingUrl("/admin/security/do/login.html")    // 默认登录页面表单提交地址
                .defaultSuccessUrl("/admin/to/main/page.html")          // 登录验证成功后跳转的地址


                .and()
                .logout()
                .logoutUrl("/admin/security/do/lagout.html")            // 退出登录处理地址
                .logoutSuccessUrl("/admin/to/login/page.html")          // 退出登录时跳转的地址

                .and()
                .csrf()
                .disable();

                http.exceptionHandling().accessDeniedHandler(myAccessDeniedHandler);
    }
}
