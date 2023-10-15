package cn.gduf.commuterSystem.service.impl;

import cn.gduf.commuterSystem.entities.PersonalInfo;
import cn.gduf.commuterSystem.mapper.PersonalInfoMapper;
import cn.gduf.commuterSystem.service.PersonalInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 18:45
 */
@Service
public class PersonalInfoServiceImpl extends ServiceImpl<PersonalInfoMapper, PersonalInfo> implements PersonalInfoService {
}
