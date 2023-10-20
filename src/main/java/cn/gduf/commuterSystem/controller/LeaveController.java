package cn.gduf.commuterSystem.controller;

import cn.gduf.commuterSystem.entities.LeaveApplicationInfo;
import cn.gduf.commuterSystem.entities.LeaveConfirmationInfo;
import cn.gduf.commuterSystem.entities.Time;
import cn.gduf.commuterSystem.service.LeaveApplicationInfoService;
import cn.gduf.commuterSystem.service.LeaveConfirmationInfoService;
import cn.gduf.commuterSystem.utils.InfoResponse;
import cn.gduf.commuterSystem.utils.MyTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

/**
 * @author LuoXuanwei
 * @date 2023/10/18 18:09
 */
@RestController
@RequestMapping("/Leave")
public class LeaveController {
    @Resource
    private LeaveConfirmationInfoService confirmationService;

    @Resource
    private LeaveApplicationInfoService applicationService;

    /**
     * 员工申请假条
     *
     * @param userSerial
     * @param time
     * @param reason
     * @param response
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("/application/{userSerial}")
    public void leaveApplication(@PathVariable("userSerial") Long userSerial,
                                 Time time,
                                 @PathVariable("reason") String reason,
                                 HttpServletResponse response) throws IOException {
        LeaveApplicationInfo applicationInfo = new LeaveApplicationInfo();
        MyTime myTime = new MyTime();

        applicationInfo.setApplicationDatetime(myTime.getNowTime());
        applicationInfo.setUserSerial(userSerial);
        applicationInfo.setReason(reason);
        applicationInfo.setStartDate((Date) time.getStartTime());
        applicationInfo.setEndDate((Date) time.getEndTime());

        boolean save = applicationService.save(applicationInfo);
        if (save) {
            LeaveConfirmationInfo confirmationInfo = new LeaveConfirmationInfo();
            confirmationInfo.setId(applicationInfo.getId());
            confirmationInfo.setIsAgree(0);

            boolean result = confirmationService.save(confirmationInfo);

            new InfoResponse(response, result, "");
        } else {
            new InfoResponse(response, save, "系统错误!请联系技术部人员!");
        }
    }

    /**
     * 相关工作人员审批假条
     *
     * @param id
     * @param session
     * @param response
     * @throws IOException
     */
    @ResponseBody
    @GetMapping("/confirmation/{id}")
    public void leaveConfirmation(@PathVariable("id") Long id,
                                  HttpSession session,
                                  HttpServletResponse response) throws IOException {
        MyTime myTime = new MyTime();
        Long userSerial = (Long) session.getAttribute("userSerial");
        LeaveConfirmationInfo confirmationInfo = confirmationService.lambdaQuery().like(LeaveConfirmationInfo::getId, id).one();
        confirmationInfo.setUserSerial(userSerial);
        confirmationInfo.setIsAgree(1);
        confirmationInfo.setApprovalDatetime(myTime.getNowTime());

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_serial", userSerial);

        boolean update = confirmationService.update(confirmationInfo, wrapper);

        new InfoResponse(response, update, "");
    }
}
