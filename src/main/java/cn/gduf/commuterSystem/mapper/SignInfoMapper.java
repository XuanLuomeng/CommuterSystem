package cn.gduf.commuterSystem.mapper;

import cn.gduf.commuterSystem.entities.SignInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 18:31
 */
@Repository
public interface SignInfoMapper extends BaseMapper<SignInfo> {
    /**
     * 通过员工编号获取当天的签到和签退时间
     *
     * @param userSerial
     * @return
     */
    List<SignInfo> getSignInfoListOfNowByUserSerial(@Param("userSerial") Long userSerial);
}
