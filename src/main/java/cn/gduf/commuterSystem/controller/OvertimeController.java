package cn.gduf.commuterSystem.controller;

import cn.gduf.commuterSystem.entities.*;
import cn.gduf.commuterSystem.service.OvertimeApplicationInfoService;
import cn.gduf.commuterSystem.service.OvertimeConfirmationInfoService;
import cn.gduf.commuterSystem.service.UserInfoService;
import cn.gduf.commuterSystem.utils.InfoResponse;
import cn.gduf.commuterSystem.utils.MyTime;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LuoXuanwei
 * @date 2023/10/21 14:09
 */
@RestController
@RequestMapping("/overtime")
public class OvertimeController {
    @Resource
    private OvertimeApplicationInfoService applicationService;

    @Resource
    private OvertimeConfirmationInfoService confirmationService;

    @Resource
    private UserInfoService userInfoService;

    /**
     * 发送加班通知
     *
     * @param time
     * @param session
     * @param response
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("/send")
    public void sendOvertimeApplication(Time time,
                                        HttpSession session,
                                        HttpServletResponse response) throws IOException {
        Long userSerial = (Long) session.getAttribute("userSerial");

        OvertimeApplicationInfo applicationInfo = new OvertimeApplicationInfo();
        applicationInfo.setUserSerial(userSerial);
        applicationInfo.setStartDatetime((Timestamp) time.getStartTime());
        applicationInfo.setEndDatetime((Timestamp) time.getEndTime());

        boolean save = applicationService.save(applicationInfo);
        if (save) {
            //广播通知建议使用rabbitmq+redis，下面以mysql批量添加来模拟实现
            Long departmentSerial = (Long) session.getAttribute("departmentSerial");

            List<UserInfo> list = userInfoService.lambdaQuery().like(UserInfo::getDepartmentSerial, departmentSerial).list();

            List<OvertimeConfirmationInfo> confirmationInfoList = new ArrayList<>();

            Long overtimeId = applicationInfo.getId();

            for (UserInfo user : list) {
                OvertimeConfirmationInfo confirmationInfo = new OvertimeConfirmationInfo();
                confirmationInfo.setOvertimeId(overtimeId);
                confirmationInfo.setUserSerial(user.getUserSerial());
                confirmationInfo.setIsAgree(0);

                confirmationInfoList.add(confirmationInfo);
            }

            boolean saveBatch = confirmationService.saveBatch(confirmationInfoList);

            new InfoResponse(response, saveBatch, "");
        }
    }

    /**
     * 查看关于员工的加班通知(包括模糊查询分页等功能)
     *
     * @param pageNum
     * @param isAgree
     * @param userName
     * @param response
     * @throws IOException
     */
    @ResponseBody
    @GetMapping("/confirm")
    public void getMySelfOvertimes(int pageNum,
                                   int isAgree,
                                   String userName,
                                   HttpServletResponse response) throws IOException {
        Page<OvertimeConfirmationInfo> page = new Page<>(pageNum, 20);

        IPage<OvertimeConfirmationInfo> iPage = confirmationService.selectOvertimeConfirmationInfos(page, userName, isAgree);

        MyPage<OvertimeConfirmationInfo> myPage = new MyPage<>();
        myPage.setTotal(iPage.getTotal());
        myPage.setSize(iPage.getSize());
        myPage.setCurrent(iPage.getCurrent());
        myPage.setRecords(iPage.getRecords());

        new InfoResponse(response, myPage);
    }

    /**
     * 查看经理自身发布的最近20条加班通知(包括模糊查询分页等功能)
     *
     * @param session
     * @param response
     */
    @ResponseBody
    @GetMapping("/confirms")
    public void getOvertimes(int pageNum,
                             HttpSession session,
                             HttpServletResponse response) throws IOException {
        Long userSerial = (Long) session.getAttribute("userSerial");
        Page<OvertimeApplicationInfo> page = new Page<>(pageNum, 20);

        IPage<OvertimeApplicationInfo> iPage = applicationService.selectOvertimeApplicationInfos(page, userSerial);

        MyPage<OvertimeApplicationInfo> myPage = new MyPage<>();
        myPage.setTotal(iPage.getTotal());
        myPage.setSize(iPage.getSize());
        myPage.setCurrent(iPage.getCurrent());
        myPage.setRecords(iPage.getRecords());

        new InfoResponse(response, myPage);
    }

    /**
     * 根据id查看经理自身管理部门某条加班通知的确认情况
     *
     * @param id
     * @param response
     */
    @ResponseBody
    @GetMapping("/confirms/{id}")
    public void getStaffsOvertimes(@PathVariable("id") Long id,
                                   HttpServletResponse response) throws IOException {
        List<OvertimeConfirmationInfo> list = confirmationService.lambdaQuery().like(OvertimeConfirmationInfo::getOvertimeId, id).list();

        new InfoResponse(response, list);
    }

    /**
     * 通过加班编号获取详细加班信息
     *
     * @param id
     * @param response
     * @throws IOException
     */
    @ResponseBody
    @GetMapping("/confirm/{id}")
    public void getOvertime(@PathVariable("id") Long id,
                            HttpServletResponse response) throws IOException {
        OvertimeApplicationInfo info = applicationService.lambdaQuery().like(OvertimeApplicationInfo::getId, id).one();

        new InfoResponse(response, info);
    }

    /**
     * 通过员工编号获取其指定范围内的部分加班通知信息
     * @param response
     * @param time
     * @param pageNum
     * @param userSerial
     * @throws IOException
     */
    @ResponseBody
    @GetMapping("/getOverTimeApplicationsByUserSerial/{userSerial}")
    public void getOverTimeApplicationsByUserSerial(HttpServletResponse response,
                                                    Time time,
                                                    int pageNum,
                                                    @PathVariable("userSerial") String userSerial) throws IOException {
        List<OvertimeConfirmationInfo> infoList = confirmationService.lambdaQuery().like(OvertimeConfirmationInfo::getUserSerial, userSerial).list();

        List<Long> list = null;
        for (OvertimeConfirmationInfo conf : infoList) {
            list.add(conf.getId());
        }

        Page<OvertimeApplicationInfo> page = new Page<>(pageNum, 20);

        IPage<OvertimeApplicationInfo> infoPage = applicationService.lambdaQuery().
                between(OvertimeApplicationInfo::getStartDatetime, time.getStartTime(), time.getEndTime()).
                in(OvertimeApplicationInfo::getId, list).
                page(page);

        new InfoResponse(response, infoPage);
    }

    /**
     * 通过加班通知编号来确认是否收到加班通知
     *
     * @param id
     * @param isAgree
     * @param session
     * @param response
     * @throws IOException
     */
    @ResponseBody
    @GetMapping("/confirm/{id}/{isAgree}")
    public void confirmOvertimeById(@PathVariable("id") Long id,
                                    @PathVariable("isAgree") int isAgree,
                                    HttpSession session,
                                    HttpServletResponse response) throws IOException {
        Long userSerial = (Long) session.getAttribute("userSerial");
        MyTime myTime = new MyTime();

        OvertimeConfirmationInfo confirmationInfo = confirmationService.lambdaQuery().like(OvertimeConfirmationInfo::getUserSerial, userSerial).like(OvertimeConfirmationInfo::getOvertimeId, id).one();
        confirmationInfo.setIsAgree(isAgree);
        confirmationInfo.setAgreeTime(myTime.getNowTime());

        UpdateWrapper wrapper = new UpdateWrapper();
        wrapper.eq("id", confirmationInfo.getId());

        boolean update = confirmationService.update(confirmationInfo, wrapper);

        new InfoResponse(response, update, "");
    }
}
