package cn.gduf.commuterSystem.service;

import cn.gduf.commuterSystem.entities.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 18:34
 */
public interface UserInfoService extends IService<UserInfo> {
    /**
     * 通过账号获取用户所有信息
     * @param userSerial
     * @return
     */
    UserInfo selectUserInfoByUserSerial(Long userSerial);
}