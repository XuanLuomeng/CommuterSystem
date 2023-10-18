package cn.gduf.commuterSystem.mapper;

import cn.gduf.commuterSystem.entities.SignInInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 18:31
 */
@Repository
public interface SignInInfoMapper extends BaseMapper<SignInInfo> {
    /**
     * 通过员工编号获取当天的签到时间
     *
     * @param userSerial
     * @return
     */
    List<SignInInfo> getSignInfoListOfNowByUserSerial(@Param("userSerial") Long userSerial);
}
