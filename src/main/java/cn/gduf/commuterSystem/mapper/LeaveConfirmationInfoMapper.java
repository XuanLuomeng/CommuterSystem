package cn.gduf.commuterSystem.mapper;

import cn.gduf.commuterSystem.entities.LeaveConfirmationInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 18:28
 */
@Repository
public interface LeaveConfirmationInfoMapper extends BaseMapper<LeaveConfirmationInfo> {
    /**
     * 通过分页功能查询所有员工假条基础信息(包括模糊查询功能)
     *
     * @param page
     * @param userName
     * @return
     */
    IPage<LeaveConfirmationInfo> getAllInfoAndUserName(Page<LeaveConfirmationInfo> page, @Param("userName") String userName, @Param("isAgree") long isAgree);
}
