package lamb.key.crowd.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author lamb
 * @since 2022-06-10
 * 汇报信息表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnPO implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer projectid;

    @ApiModelProperty(value = "0 - 实物回报， 1 虚拟物品回报")
    private Integer type;

    @ApiModelProperty(value = "支持金额")
    private Integer supportmoney;

    @ApiModelProperty(value = "回报内容")
    private String content;

    @ApiModelProperty(value = "回报产品限额，“0”为不限回报数量")
    private Integer count;

    @ApiModelProperty(value = "是否设置单笔限购")
    private Integer signalpurchase;

    @ApiModelProperty(value = "具体限购数量")
    private Integer purchase;

    @ApiModelProperty(value = "运费，“0”为包邮")
    private Integer freight;

    @ApiModelProperty(value = "0 - 不开发票， 1 - 开发票")
    private Integer invoice;

    @ApiModelProperty(value = "项目结束后多少天向支持者发送回报")
    private Integer returndate;

    @ApiModelProperty(value = "说明图片路径")
    private String describPicPath;


}
