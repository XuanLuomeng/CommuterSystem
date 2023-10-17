package cn.gduf.commuterSystem.service.impl;

import cn.gduf.commuterSystem.entities.PersonalInfo;
import cn.gduf.commuterSystem.mapper.PersonalInfoMapper;
import cn.gduf.commuterSystem.service.PersonalInfoService;
import cn.gduf.commuterSystem.utils.EncryptByMd5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 18:45
 */
@Service
public class PersonalInfoServiceImpl extends ServiceImpl<PersonalInfoMapper, PersonalInfo> implements PersonalInfoService {
    @Resource
    private PersonalInfoMapper personalInfoMapper;

    /**
     * 通过账号和密码检验密码正确性(用于登录与修改密码)
     * 通过数据库查询得到的personal的password是经过加密算法，
     * 所以需要将personalInfo的密码与personal的加密盐进行加密后密码比对来确定密码是否正确
     *
     * @param personalInfo
     * @return
     */
    @Override
    public boolean checkPassword(PersonalInfo personalInfo) {
        PersonalInfo personal = personalInfoMapper.getPersonalInfoByUserSerial(personalInfo.getUserSerial());

        EncryptByMd5 encryptByMd5 = new EncryptByMd5(personalInfo.getPassword(), personal.getSalt());

        if (encryptByMd5.getSimpleHash().equals(personal.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public PersonalInfo selectPersonalInfoByUserSerial(Long userSerial) {
        PersonalInfo personalInfo = personalInfoMapper.getPersonalInfoByUserSerial(userSerial);
        return personalInfo;
    }
}
