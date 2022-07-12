package lamb.key.service.impl;

import lamb.key.entity.Admin;
import lamb.key.mapper.AdminMapper;
import lamb.key.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JoinYang
 * @date 2022/5/6 19:24
 */
@Service("adminServiceImpl")
public class AdminServiceImpl implements AdminService, UserDetailsService {

    @Autowired
    private AdminMapper adminMapper;

    // 自定义的登录验证，如果是角色验证则前缀一定要带ROLE_,如果是权限验证则不需要带，权限认证和角色认证同放在一个集合里面，它们通过ROLE_前缀进行识别
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Admin admin = adminMapper.getAdminByAcctName(s);

        if (admin!=null){
            UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                    admin.getUser_name(),admin.getUser_pswd(), AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_师兄")
            );
            return userDetails;
        }
        throw new UsernameNotFoundException("用户没有找到");
    }
}
