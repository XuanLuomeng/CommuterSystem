package cn.gduf.commuterSystem.service.impl;

import cn.gduf.commuterSystem.entities.SignOutInfo;
import cn.gduf.commuterSystem.mapper.SignOutInfoMapper;
import cn.gduf.commuterSystem.service.SignOutInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 18:48
 */
@Service
public class SignOutInfoServiceImpl extends ServiceImpl<SignOutInfoMapper, SignOutInfo> implements SignOutInfoService {
    @Resource
    private SignOutInfoMapper signOutInfoMapper;

    @Override
    public Integer getToDayCountByUserSerial(Long userSerial) {
        List<SignOutInfo> signOutInfoList = signOutInfoMapper.getSignInfoListOfNowByUserSerial(userSerial);
        int size = signOutInfoList.size();
        return size;
    }
}
