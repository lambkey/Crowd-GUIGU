package lamb.key.crowd.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author lamb
 * @since 2022-06-10
 *发起人确认信息表
 */
@Data

public class MemberConfirmInfoPO implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "会员 id")
    private Integer memberid;

    @ApiModelProperty(value = "易付宝企业账号")
    private String paynum;

    @ApiModelProperty(value = "法人身份证号")
    private String cardnum;



}
