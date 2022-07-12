package net.seehope.crowd.service.securityservice;

import net.seehope.crowd.entity.Admin;
import net.seehope.crowd.entity.Role;
import net.seehope.crowd.service.api.AdminService;
import net.seehope.crowd.service.api.AuthService;
import net.seehope.crowd.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JoinYang
 * @date 2022/5/8 16:32
 */
@Service
public class AdminDetailService implements UserDetailsService {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthService authService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // 根据用户名查询用户
        Admin admin = adminService.getAdminByLogName(s);

        // 获取用户id
        Integer adminId = admin.getId();

        // 根据用户id获取用户已经分配的角色
        List<Role> roles = roleService.getAssignedRole(adminId);

        // 根据用户id获取已分配的角色的已分配的权限
        List<String> authNames = authService.getAssignedAuthNameofAuthFromRoleFromAdminByAdminId(adminId);

        // 在security权限集合中添加角色、权限， 注意：角色要带ROLE_前缀，权限则不用
        List<GrantedAuthority> authorityList = new ArrayList<>();

        // 往authorityList中添加角色
        for (Role role: roles
             ) {
            SimpleGrantedAuthority simpleGrantedAuthority =new SimpleGrantedAuthority("ROLE_"+role.getName());

            authorityList.add(simpleGrantedAuthority);
        }

        // 往authorityList中添加角色权限
        for (String authName:authNames
             ) {
            SimpleGrantedAuthority simpleGrantedAuthority =new SimpleGrantedAuthority(authName);

            authorityList.add(simpleGrantedAuthority);
        }

        // 给security返回userDetails
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(admin.getUser_Name(),admin.getUser_Pswd(),authorityList);

        // 接下来要去配置类中装配UserDetailService
        return userDetails;
    }

}
