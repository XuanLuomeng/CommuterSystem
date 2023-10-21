package cn.gduf.commuterSystem.service;

import cn.gduf.commuterSystem.entities.OvertimeConfirmationInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 18:37
 */
public interface OvertimeConfirmationInfoService extends IService<OvertimeConfirmationInfo> {
    IPage<OvertimeConfirmationInfo> selectOvertimeConfirmationInfos(Page<OvertimeConfirmationInfo> page,String userName,int isAgree);
}
