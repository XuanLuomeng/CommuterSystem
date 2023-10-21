package cn.gduf.commuterSystem.service;

import cn.gduf.commuterSystem.entities.OvertimeApplicationInfo;
import cn.gduf.commuterSystem.entities.OvertimeConfirmationInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.annotation.Resource;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 18:36
 */
public interface OvertimeApplicationInfoService extends IService<OvertimeApplicationInfo> {
    IPage<OvertimeApplicationInfo> selectOvertimeApplicationInfos(Page<OvertimeApplicationInfo> page, Long userSerial);
}
