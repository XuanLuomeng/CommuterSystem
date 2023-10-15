package cn.gduf.commuterSystem.service.impl;

import cn.gduf.commuterSystem.entities.DepartmentInfo;
import cn.gduf.commuterSystem.mapper.DepartmentInfoMapper;
import cn.gduf.commuterSystem.service.DepartmentInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 18:40
 */
@Service
public class DepartmentInfoServiceImpl extends ServiceImpl<DepartmentInfoMapper, DepartmentInfo>implements DepartmentInfoService {
}
