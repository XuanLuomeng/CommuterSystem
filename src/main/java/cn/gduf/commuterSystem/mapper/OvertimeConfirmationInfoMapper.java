package cn.gduf.commuterSystem.mapper;

import cn.gduf.commuterSystem.entities.OvertimeConfirmationInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 18:30
 */
@Repository
public interface OvertimeConfirmationInfoMapper extends BaseMapper<OvertimeConfirmationInfo> {
    IPage<OvertimeConfirmationInfo> getAllConfirmationInfos(Page<OvertimeConfirmationInfo> page, @Param("userName") String userName,@Param("isAgree") Long isAgree);
}
