package cn.gduf.commuterSystem.service.impl;

import cn.gduf.commuterSystem.entities.SabbaticalConfirmationInfo;
import cn.gduf.commuterSystem.mapper.SabbaticalConfirmationInfoMapper;
import cn.gduf.commuterSystem.service.SabbaticalConfirmationInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 18:46
 */
@Service
public class SabbaticalConfirmationInfoServiceImpl extends ServiceImpl<SabbaticalConfirmationInfoMapper, SabbaticalConfirmationInfo> implements SabbaticalConfirmationInfoService {
    @Resource
    private SabbaticalConfirmationInfoMapper confirmationInfoMapper;

    @Override
    public IPage<SabbaticalConfirmationInfo> selectSabbaticalConfirmationInfos(Page<SabbaticalConfirmationInfo> page, String userName, Long isAgree) {
        IPage<SabbaticalConfirmationInfo> iPage = confirmationInfoMapper.getAllConfirmationInfos(page, userName, isAgree);
        return iPage;
    }
}
