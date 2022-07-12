package lamb.key.crowd.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author JoinYang
 * @date 2022/6/21 20:23
 * @Version 1.0
 *
 * 项目详细展示
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailProjectVO {
    private Integer projectId;

    private String projectName;

    // 项目简介
    private String projectDesc;

    // 关注人数
    private Integer followerCount;

    // 审核状态0 1 2 3
    private Integer status;

    // 不同审核状态对应的文字
    private String statusText;

    // 目标筹集金额
    private Integer money;

    // 已经筹集了多少金额
    private Integer supportMoney;

    // 完成百分比
    private Integer percentage;

    // 众筹所需的天数
    private Integer day;

    // 审核通过的日期
    private String deployDate;

    // 剩余天数
    private Integer lastDay;

    // 有多少人支持
    private Integer supporterCount;

    // 项目头图
    private String headerPicturePath;

    // 详细图片路径
    private List<String> detailPicture;

    // 回报详细信息
    private List<DetailReturnVO> detailReturnVOList;
}
