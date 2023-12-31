package cn.gduf.commuterSystem.service;

import cn.gduf.commuterSystem.entities.SabbaticalConfirmationInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 18:38
 */
public interface SabbaticalConfirmationInfoService extends IService<SabbaticalConfirmationInfo> {
    /**
     * 分页查询调休审批信息
     * @param page
     * @param userName
     * @param isAgree
     * @return
     */
    IPage<SabbaticalConfirmationInfo> selectSabbaticalConfirmationInfos(Page<SabbaticalConfirmationInfo> page, String userName, Long isAgree);
}
