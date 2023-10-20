package cn.gduf.commuterSystem.service.impl;

import cn.gduf.commuterSystem.entities.SignInfo;
import cn.gduf.commuterSystem.mapper.SignInfoMapper;
import cn.gduf.commuterSystem.service.SignInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 18:47
 */
@Service
public class SignInfoServiceImpl extends ServiceImpl<SignInfoMapper, SignInfo> implements SignInfoService {
    @Resource
    private SignInfoMapper signInInfoMapper;

    @Override
    public List<SignInfo> getToDayCountByUserSerial(Long userSerial) {
        List<SignInfo> signInInfoList = signInInfoMapper.getSignInfoListOfNowByUserSerial(userSerial);
        return signInInfoList;
    }
}
