package lamb.key.crowd.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author JoinYang
 * @date 2022/6/10 1:02
 * @Version 1.0
 * 发起人信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberLaunchInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;
    // 简单介绍
     private String descriptionSimple;

     // 详细介绍
     private String descriptionDetail;

     // 联系电话
     private String phoneNum;

    // 客服电话
     private String serviceNum;
}