package lamb.key.crowd.service.imp;

import lamb.key.crowd.mapper.*;
import lamb.key.crowd.po.MemberConfirmInfoPO;
import lamb.key.crowd.po.MemberLaunchInfoPO;
import lamb.key.crowd.po.ProjectPO;
import lamb.key.crowd.po.ReturnPO;
import lamb.key.crowd.service.ProjectService;
import lamb.key.crowd.vo.*;
import net.seehope.crowd.util.JSONResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author JoinYang
 * @date 2022/6/10 14:11
 * @Version 1.0
 *
 * 1.会员项目信息存入
 * 2.项目主页显示项目分类信息
 * 3.点击分类项目进入查看具体项目的具体详细信息
 */
@Transactional(readOnly = true)
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private TProjectMapper tProjectMapper;

    @Autowired
    private TProjectItemPicMapper tProjectItemPicMapper;

    @Autowired
    private TMemberLaunchInfoMapper tMemberLaunchInfoMapper;

    @Autowired
    private TMemberConfirmInfoMapper tMemberConfirmInfoMapper;

    @Autowired
    private TReturnMapper tReturnMapper;

    @Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
    @Override
    public JSONResult saveProject(ProjectVO projectVO,Integer memberId) {

        // 一、保存ProjectPO对象
        // 1.创建空的ProjectPO对象
        ProjectPO projectPO =new ProjectPO();


        // 2.将VO对象转换为PO对象

        try {
            BeanUtils.copyProperties(projectVO,projectPO);
        // bug修复
        // ①把MemberId设置到projectPO中
            projectPO.setMemberid(memberId);
        // ②生成创建时间存入
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
            String format = simpleDateFormat.format(new Date());
            projectPO.setCreatedate(format);
        // ③status设置成0，表示即将开始
            projectPO.setStatus(0);


        // 3.保存projectPO对象
        // 为了能够获取到projectPO保存后的自增主键，需要到TProjectMapper.xml文件中配置
        // <insert id="insertSelective" useGeneratedKeys="true" keyProperty="id" parameterType="com.hxw.crowd.entity.po.ProjectPO">

            tProjectMapper.insertSelective(projectPO);


        // 4.从projectPO对象这里获取自增主键

            Integer projectId = projectPO.getId();


        // 二、保存项目、分类的关联关系信息
        // 1.从ProjectVO对象中获取TypeIdList
            List<Integer> typeIdList = projectVO.getTypeIdList();

            tProjectMapper.insertTypeRelationship(typeIdList,projectId);


        // 三、保存项目、标签的关联关系信息

            List<Integer> tagIdList = projectVO.getTagIdList();

            tProjectMapper.insertTagRelationship(tagIdList,projectId);


        // 四、保存项目中详情图片路径信息

            List<String> detailPicturePathList = projectVO.getDetailPicturePathList();

            tProjectItemPicMapper.savePathList(detailPicturePathList,projectId);


        // 五、保存项目发起人信息
            MemberLaunchInfoVO memberLaunchInfoVO = projectVO.getMemberLaunchInfoVO();

            MemberLaunchInfoPO memberLaunchInfoPO = new MemberLaunchInfoPO();

            BeanUtils.copyProperties(memberLaunchInfoVO,memberLaunchInfoPO);

            memberLaunchInfoPO.setMemberid(memberId);

            tMemberLaunchInfoMapper.saveMemberLaunchInfo(memberLaunchInfoPO);

        // 六、保存项目回报信息
            List<ReturnVO> returnVOList = projectVO.getReturnVOList();

            List<ReturnPO> returnPOList = new ArrayList<>();

            for (ReturnVO returnVO : returnVOList) {

                ReturnPO returnPO = new ReturnPO();

                BeanUtils.copyProperties(returnVO,returnPO);

                returnPOList.add(returnPO);
            }
            tReturnMapper.saveReturnInfo(returnPOList,projectId);


        // 七、保存项目的确认信息
            MemberConfirmInfoVO memberConfirmInfoVO = projectVO.getMemberConfirmInfoVO();

            MemberConfirmInfoPO memberConfirmInfoPO = new MemberConfirmInfoPO();

            BeanUtils.copyProperties(memberConfirmInfoVO,memberConfirmInfoPO);

            memberConfirmInfoPO.setMemberid(memberId);

            System.out.println(memberConfirmInfoPO);

            tMemberConfirmInfoMapper.saveMemberConfirmInfo(memberConfirmInfoPO);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
    }

    // 3.点击分类项目进入查看具体项目的具体详细信息
    @Override
    public DetailProjectVO getDetailProject(Integer projectId) {
        // 1.查询得到DetailProjectVO对象
        DetailProjectVO detailProjectVO = tProjectMapper.selectDetailProjectVO(projectId);


        // 2.根据status确定statusText
        Integer status = detailProjectVO.getStatus();
        switch (status){
            case 0:
                detailProjectVO.setStatusText("审核中");
                break;
            case 1:
                detailProjectVO.setStatusText("众筹中");
            case 2:
                detailProjectVO.setStatusText("众筹成功");
            case 3:
                detailProjectVO.setStatusText("已关闭");
                break;

                default:
                break;
        }

        // 3.根据deployDate计算lastDay
        // 2020-10-15
        String deployDate = detailProjectVO.getDeployDate();

        // 获取当前日期
        Date currentDay = new Date();

        // 把众筹日期解析成Date类型
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parse = format.parse(deployDate);

            // 获取当前日期的时间戳
            long time = currentDay.getTime();

            // 获取众筹日期的时间戳
            long time1 = parse.getTime();

            // 两个时间戳相减计算当前已经过去的时间
            long pastDays = (time-time1)/1000/60/60/24;

            // 获取总的众筹天数
            Integer day = detailProjectVO.getDay();

            // 剩余时间
            Integer lastDay = (int)(day-pastDays);

            detailProjectVO.setLastDay(lastDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // 4.随机生成关注人数
        int random = (int)(Math.random()*10000+1000);
        detailProjectVO.setFollowerCount(random);

        return detailProjectVO;
    }

    // 2.获取项目分类List
    // 分类List里面又有多项目详细信息
    @Override
    public List<PortalTypeVO> getPortalTypeVO() {
        return tProjectMapper.selectPortalTypeVOList();
    }
}
