package lamb.key.crowd.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author JoinYang
 * @date 2022/6/3 17:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO implements Serializable {
    private String loginacct;

    private String userpswd;

    private String username;

    private String email;

    private String phoneNum;

    private String code;
}
