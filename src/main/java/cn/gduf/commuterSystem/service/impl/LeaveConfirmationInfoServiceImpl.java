package cn.gduf.commuterSystem.service.impl;

import cn.gduf.commuterSystem.entities.LeaveConfirmationInfo;
import cn.gduf.commuterSystem.mapper.LeaveConfirmationInfoMapper;
import cn.gduf.commuterSystem.service.LeaveConfirmationInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 18:42
 */
@Service
public class LeaveConfirmationInfoServiceImpl extends ServiceImpl<LeaveConfirmationInfoMapper, LeaveConfirmationInfo> implements LeaveConfirmationInfoService {
    @Resource
    private LeaveConfirmationInfoMapper leaveConfirmationInfoMapper;

    @Override
    public IPage<LeaveConfirmationInfo> selectLeaveConfirmationInfos(Page<LeaveConfirmationInfo> page, String userName, long isAgree) {
        IPage<LeaveConfirmationInfo> iPage = leaveConfirmationInfoMapper.getAllInfoAndUserName(page, userName, isAgree);
        return iPage;
    }
}
