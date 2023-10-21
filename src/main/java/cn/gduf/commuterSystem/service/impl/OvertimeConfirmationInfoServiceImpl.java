package cn.gduf.commuterSystem.service.impl;

import cn.gduf.commuterSystem.entities.OvertimeConfirmationInfo;
import cn.gduf.commuterSystem.mapper.OvertimeConfirmationInfoMapper;
import cn.gduf.commuterSystem.service.OvertimeConfirmationInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 18:44
 */
@Service
public class OvertimeConfirmationInfoServiceImpl extends ServiceImpl<OvertimeConfirmationInfoMapper, OvertimeConfirmationInfo> implements OvertimeConfirmationInfoService {
    @Resource
    private OvertimeConfirmationInfoMapper confirmationInfoMapper;

    @Override
    public IPage<OvertimeConfirmationInfo> selectOvertimeConfirmationInfos(Page<OvertimeConfirmationInfo> page, String userName, int isAgree) {
        IPage<OvertimeConfirmationInfo> confirmationInfos = confirmationInfoMapper.getAllConfirmationInfos(page, userName, (long) isAgree);
        return confirmationInfos;
    }
}
