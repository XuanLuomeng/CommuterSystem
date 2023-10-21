package cn.gduf.commuterSystem.service.impl;

import cn.gduf.commuterSystem.entities.OvertimeApplicationInfo;
import cn.gduf.commuterSystem.mapper.OvertimeApplicationInfoMapper;
import cn.gduf.commuterSystem.service.OvertimeApplicationInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 18:43
 */
@Service
public class OvertimeApplicationInfoServiceImpl extends ServiceImpl<OvertimeApplicationInfoMapper, OvertimeApplicationInfo> implements OvertimeApplicationInfoService {
    @Resource
    private OvertimeApplicationInfoMapper applicationInfoMapper;

    @Override
    public IPage<OvertimeApplicationInfo> selectOvertimeApplicationInfos(Page<OvertimeApplicationInfo> page, Long userSerial) {
        IPage<OvertimeApplicationInfo> infos = applicationInfoMapper.getAllApplicationInfos(page, userSerial);
        return infos;
    }
}
