package lamb.key.crowd.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lamb
 * @since 2022-06-10
 * 项目发起人信息表
 */
@Data

public class MemberLaunchInfoPO implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "会员 id")
    private Integer memberid;

    @ApiModelProperty(value = "简单介绍")
    private String descriptionSimple;

    @ApiModelProperty(value = "详细介绍")
    private String descriptionDetail;

    @ApiModelProperty(value = "联系电话")
    private String phoneNum;

    @ApiModelProperty(value = "客服电话")
    private String serviceNum;


}
