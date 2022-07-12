package lamb.key.crowd.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JoinYang
 * @date 2022/6/21 20:15
 * @Version 1.0
 *
 * 显示项目详情回报信息类
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailReturnVO {
    // 回报信息主键
    private Integer returnId;

    // 当前档位需支持的金额
    private Integer supportMoney;

    // 单笔限购，取值为0时无限额，取值 为1为具体限额
    private Integer signalPurchase;

    // 具体限额的数量
    private Integer purchase;

    // 当前该档位支持者数量,为了简单，随机生成
    private Integer supporterCount;

    // 运费，取值为0表示包邮
    private Integer freight;

    // 众筹成功后多少天发货
    private Integer returnDate;

    // 回报内容
    private String content;
}
