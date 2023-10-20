package cn.gduf.commuterSystem.mapper;

import cn.gduf.commuterSystem.entities.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    /**
     * 通过分页功能查询所有员工基础信息(包括模糊查询功能)
     * @param page
     * @param userName
     * @return
     */
    IPage<UserInfo> selectAll(Page<UserInfo> page,@Param("userName")String userName);
}
