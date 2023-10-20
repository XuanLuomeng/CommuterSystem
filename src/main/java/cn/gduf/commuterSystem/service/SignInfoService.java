package cn.gduf.commuterSystem.service;

import cn.gduf.commuterSystem.entities.SignInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 18:38
 */
public interface SignInfoService extends IService<SignInfo> {
    /**
     * 通过员工编号获取当天签到列表
     * @param userSerial
     * @return
     */
    List<SignInfo> getToDayCountByUserSerial(Long userSerial);
}
