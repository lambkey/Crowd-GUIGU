package lamb.key.crowd.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lamb
 * @since 2022-06-10
 * 项目表
 *
 */
@Data

public class ProjectPO implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目描述")
    private String projectDescription;

    @ApiModelProperty(value = "筹集金额")
    private Long money;

    @ApiModelProperty(value = "筹集天数")
    private Integer day;

    @ApiModelProperty(value = "0-即将开始，1-众筹中，2-众筹成功，3-众筹失败 ")
    private Integer status;

    @ApiModelProperty(value = "项目发起时间")
    private String deploydate;

    @ApiModelProperty(value = "已筹集到的金额")
    private Long supportmoney;

    @ApiModelProperty(value = "支持人数")
    private Integer supporter;

    @ApiModelProperty(value = "百分比完成度")
    private Integer completion;

    @ApiModelProperty(value = "发起人的会员 id")
    private Integer memberid;

    @ApiModelProperty(value = "项目创建时间")
    private String createdate;

    @ApiModelProperty(value = "关注人数")
    private Integer follower;

    @ApiModelProperty(value = "头图路径")
    private String headerPicturePath;


}
