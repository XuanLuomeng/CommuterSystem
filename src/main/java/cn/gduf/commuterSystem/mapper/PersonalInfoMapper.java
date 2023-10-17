package cn.gduf.commuterSystem.mapper;

import cn.gduf.commuterSystem.entities.PersonalInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 18:30
 */
@Repository
public interface PersonalInfoMapper extends BaseMapper<PersonalInfo> {
    /**
     * 通过userSerial获取用户所有信息
     *
     * @param userSerial
     * @return
     */
    PersonalInfo getPersonalInfoByUserSerial(@Param("userSerial") Long userSerial);
}
