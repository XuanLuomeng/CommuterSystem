package cn.gduf.commuterSystem.mapper;

import cn.gduf.commuterSystem.entities.OvertimeApplicationInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 18:29
 */
@Repository
public interface OvertimeApplicationInfoMapper extends BaseMapper<OvertimeApplicationInfo> {
    IPage<OvertimeApplicationInfo> getAllApplicationInfos(Page<OvertimeApplicationInfo> page,@Param("userSerial") Long userSerial);

}
