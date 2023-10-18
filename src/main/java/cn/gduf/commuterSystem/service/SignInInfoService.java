package cn.gduf.commuterSystem.service;

import cn.gduf.commuterSystem.entities.SignInInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 18:38
 */
public interface SignInInfoService extends IService<SignInInfo> {
    /**
     * 通过员工编号获取当天签到次数
     * @param userSerial
     * @return
     */
    Integer getToDayCountByUserSerial(Long userSerial);
}
