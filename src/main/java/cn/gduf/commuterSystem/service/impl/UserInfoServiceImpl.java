package cn.gduf.commuterSystem.service.impl;

import cn.gduf.commuterSystem.entities.UserInfo;
import cn.gduf.commuterSystem.mapper.UserInfoMapper;
import cn.gduf.commuterSystem.service.UserInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 18:49
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo selectUserInfoByUserSerial(Long userSerial) {
        UserInfo userInfo = userInfoMapper.getUserInfoByUserSerial(userSerial);
        return userInfo;
    }

    @Override
    public IPage<UserInfo> selectUserInfos(Page<UserInfo> page, String userName) {
        IPage<UserInfo> iPage = userInfoMapper.selectAll(page, userName);
        return iPage;
    }
}
