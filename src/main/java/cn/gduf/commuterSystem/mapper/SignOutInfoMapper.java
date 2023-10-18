package cn.gduf.commuterSystem.mapper;

import cn.gduf.commuterSystem.entities.SignOutInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 18:32
 */
@Repository
public interface SignOutInfoMapper extends BaseMapper<SignOutInfo> {
    /**
     * 通过员工编号获取当天的签退时间
     *
     * @param userSerial
     * @return
     */
    List<SignOutInfo> getSignInfoListOfNowByUserSerial(@Param("userSerial") Long userSerial);
}
