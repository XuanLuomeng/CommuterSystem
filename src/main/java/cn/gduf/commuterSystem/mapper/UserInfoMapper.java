package cn.gduf.commuterSystem.mapper;

import cn.gduf.commuterSystem.entities.PersonalInfo;
import cn.gduf.commuterSystem.entities.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 18:27
 */
@Repository
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    /**
     * 通过userSerial获取用户所有信息
     *
     * @param userSerial
     * @return
     */
    UserInfo getUserInfoByUserSerial(@Param("userSerial") Long userSerial);
}
