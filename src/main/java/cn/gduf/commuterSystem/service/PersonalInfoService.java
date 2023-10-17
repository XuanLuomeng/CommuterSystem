package cn.gduf.commuterSystem.service;

import cn.gduf.commuterSystem.entities.PersonalInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 18:37
 */
public interface PersonalInfoService extends IService<PersonalInfo> {
    /**
     * 检测密码正确性
     * @param personalInfo
     * @return
     */
    boolean checkPassword(PersonalInfo personalInfo);

    /**
     * 通过账号获取用户所有信息
     * @param userSerial
     * @return
     */
    PersonalInfo selectPersonalInfoByUserSerial(Long userSerial);
}
