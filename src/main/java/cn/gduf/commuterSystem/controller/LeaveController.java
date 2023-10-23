package cn.gduf.commuterSystem.controller;

import cn.gduf.commuterSystem.entities.*;
import cn.gduf.commuterSystem.service.LeaveApplicationInfoService;
import cn.gduf.commuterSystem.service.LeaveConfirmationInfoService;
import cn.gduf.commuterSystem.utils.InfoResponse;
import cn.gduf.commuterSystem.utils.MyTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
                                 String reason,
                                 HttpServletResponse response) throws IOException {
        LeaveApplicationInfo applicationInfo = new LeaveApplicationInfo();
        MyTime myTime = new MyTime();

        applicationInfo.setApplicationDatetime(myTime.getNowTime());
        applicationInfo.setUserSerial(userSerial);
        applicationInfo.setReason(reason);
        applicationInfo.setStartDate(new Date(time.getStartTime().getTime()));
        applicationInfo.setEndDate(new Date(time.getEndTime().getTime()));

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
     * 相关工作人员通过假条id审批假条
     *
     * @param id
     * @param isAgree
     * @param session
     * @param response
     * @throws IOException
     */
    @ResponseBody
    @GetMapping("/confirmation/{id}/{isAgree}")
    public void leaveConfirmation(@PathVariable("id") Long id,
                                  @PathVariable("isAgree") int isAgree,
                                  HttpSession session,
                                  HttpServletResponse response) throws IOException {
        MyTime myTime = new MyTime();
        Long userSerial = (Long) session.getAttribute("userSerial");
        System.out.println(userSerial);
        LeaveConfirmationInfo confirmationInfo = confirmationService.getById(id);
        confirmationInfo.setUserSerial(userSerial);
        confirmationInfo.setIsAgree(isAgree);
        confirmationInfo.setApprovalDatetime(myTime.getNowTime());

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", id);

        boolean update = confirmationService.update(confirmationInfo, wrapper);

        new InfoResponse(response, update, "");
    }

    /**
     * 分页获取所有用户基础信息(包括模糊查询,也可用于查看个人,isAgree为-1表示不分是否审批查询,userName为@表示不以姓名模糊查询)
     *
     * @param response
     * @param pageNum
     * @param userName
     * @throws IOException
     */
    @ResponseBody
    @GetMapping("/allLeaveApplication")
    public void allLeaveApplication(HttpServletResponse response,
                                    int pageNum,
                                    String userName,
                                    Integer isAgree) throws IOException {
        isAgree = isAgree == 0 || isAgree == 1 ? isAgree : -1;
        userName = userName.equals("@") ? null : userName;

        Page<LeaveConfirmationInfo> page = new Page<>(pageNum, 20);

        IPage<LeaveConfirmationInfo> iPage = confirmationService.selectLeaveConfirmationInfos(page, userName, isAgree);

        MyPage<LeaveConfirmationInfo> myPage = new MyPage<>();
        myPage.setTotal(iPage.getTotal());
        myPage.setSize(iPage.getSize());
        myPage.setCurrent(iPage.getCurrent());
        myPage.setRecords(iPage.getRecords());

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(myPage);
        //设置content-type防止乱码问题
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    /**
     * 通过假条编号获取员工详细假条信息
     *
     * @param response
     * @param id
     * @throws IOException
     */
    @ResponseBody
    @GetMapping("/getLeaveApplicationById/{id}")
    public void getLeaveApplicationById(HttpServletResponse response,
                                        @PathVariable("id") long id) throws IOException {
        LeaveConfirmationInfo info = confirmationService.lambdaQuery().like(LeaveConfirmationInfo::getId, id).one();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
