package cn.gduf.commuterSystem.service;

import cn.gduf.commuterSystem.entities.LeaveConfirmationInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 18:36
 */
public interface LeaveConfirmationInfoService extends IService<LeaveConfirmationInfo> {
    /**
     * 分页获取请假确认信息
     *
     * @param page
     * @param userName
     * @param isAgree
     * @return
     */
    IPage<LeaveConfirmationInfo> selectLeaveConfirmationInfos(Page<LeaveConfirmationInfo> page, String userName, long isAgree);
}
