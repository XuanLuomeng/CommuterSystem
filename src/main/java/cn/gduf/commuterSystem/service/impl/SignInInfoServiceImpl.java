package cn.gduf.commuterSystem.service.impl;

import cn.gduf.commuterSystem.entities.SignInInfo;
import cn.gduf.commuterSystem.mapper.SignInInfoMapper;
import cn.gduf.commuterSystem.service.SignInInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 18:47
 */
@Service
public class SignInInfoServiceImpl extends ServiceImpl<SignInInfoMapper, SignInInfo> implements SignInInfoService {
    @Resource
    private SignInInfoMapper signInInfoMapper;

    @Override
    public Integer getToDayCountByUserSerial(Long userSerial) {
        List<SignInInfo> signInInfoList = signInInfoMapper.getSignInfoListOfNowByUserSerial(userSerial);
        int size = signInInfoList.size();
        return size;
    }
}
