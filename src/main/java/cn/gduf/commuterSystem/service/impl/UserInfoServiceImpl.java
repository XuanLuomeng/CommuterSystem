package cn.gduf.commuterSystem.service.impl;

import cn.gduf.commuterSystem.entities.UserInfo;
import cn.gduf.commuterSystem.mapper.UserInfoMapper;
import cn.gduf.commuterSystem.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 18:49
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
}
