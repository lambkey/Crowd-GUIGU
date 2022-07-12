package lamb.key.crowd.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author JoinYang
 * @date 2022/6/10 1:08
 * @Version 1.0
 * 回报信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnVO implements Serializable {
    private static final long serialVersionUID = 1L;
    // 回报类型：0 - 实物回报， 1 虚拟物品回报
    private Integer type;

    // 支持金额
    private Integer supportmoney;

    // 回报内容介绍
    private String content;

    // 总回报数量，0 为不限制
    private Integer count;

    // 是否限制单笔购买数量，0 表示不限购，正数表示限购
    private Integer signalpurchase;

    // 如果单笔限购，那么具体的限购数量
    private Integer purchase;

    // 众筹成功后多少天发货
    private Integer returndate;


    // 运费，取值为0表示包邮
    private Integer freight;
}