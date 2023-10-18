package cn.gduf.commuterSystem.service;

import cn.gduf.commuterSystem.entities.SignOutInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 18:39
 */
public interface SignOutInfoService extends IService<SignOutInfo> {
    /**
     * 通过员工编号获取当天签退次数
     * @param userSerial
     * @return
     */
    Integer getToDayCountByUserSerial(Long userSerial);
}
