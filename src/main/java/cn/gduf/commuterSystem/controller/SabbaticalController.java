package cn.gduf.commuterSystem.controller;

import cn.gduf.commuterSystem.entities.MyPage;
import cn.gduf.commuterSystem.entities.SabbaticalApplicationInfo;
import cn.gduf.commuterSystem.entities.SabbaticalConfirmationInfo;
import cn.gduf.commuterSystem.service.SabbaticalApplicationInfoService;
import cn.gduf.commuterSystem.service.SabbaticalConfirmationInfoService;
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

/**
 * @author LuoXuanwei
 * @date 2023/10/21 10:29
 */
@RestController
@RequestMapping("/sabbatical")
public class SabbaticalController {
    @Resource
    private SabbaticalApplicationInfoService applicationService;

    @Resource
    private SabbaticalConfirmationInfoService confirmationService;

    /**
     * 申请调休
     *
     * @param dayString
     * @param dayTargetString
     * @param session
     * @param response
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("/application")
    public void sabbaticalApplication(String dayString,
                                      String dayTargetString,
                                      HttpSession session,
                                      HttpServletResponse response) throws IOException {
        String[] day = dayString.split(",");
        String[] dayTarget = dayTargetString.split(",");
        Long userSerial = (Long) session.getAttribute("userSerial");
        if (day.length != dayTarget.length) {
            new InfoResponse(response, false, "调休日期数不相等");
        } else {
            MyTime myTime = new MyTime();

            SabbaticalApplicationInfo applicationInfo = new SabbaticalApplicationInfo();
            applicationInfo.setUserSerial(userSerial);
            applicationInfo.setDate(dayString);
            applicationInfo.setTargetDate(dayTargetString);
            applicationInfo.setApplicationDatetime(myTime.getNowTime());

            boolean save = applicationService.save(applicationInfo);

            if (save) {
                SabbaticalConfirmationInfo confirmationInfo = new SabbaticalConfirmationInfo();
                confirmationInfo.setId(applicationInfo.getId());
                confirmationInfo.setIsAgree(0);
                boolean result = confirmationService.save(confirmationInfo);
                new InfoResponse(response, result, "");
            }
        }
    }

    /**
     * 分页获取所有员工调休基础信息(包括模糊查询,也可用于查看个人)
     *
     * @param response
     * @param pageNum
     * @param userName
     * @param isAgree
     * @throws IOException
     */
    @ResponseBody
    @GetMapping("/allSabbatical")
    public void getAllSabbatical(HttpServletResponse response,
                                 int pageNum,
                                 String userName,
                                 Long isAgree) throws IOException {
        Page<SabbaticalConfirmationInfo> page = new Page<>(pageNum, 20);

        IPage<SabbaticalConfirmationInfo> iPage = confirmationService.selectSabbaticalConfirmationInfos(page, userName, isAgree);

        MyPage<SabbaticalConfirmationInfo> myPage = new MyPage<>();
        myPage.setTotal(iPage.getTotal());
        myPage.setSize(iPage.getSize());
        myPage.setCurrent(iPage.getCurrent());
        myPage.setRecords(iPage.getRecords());

        new InfoResponse(response, myPage);
    }

    /**
     * 通过调休id获取详细调休信息
     *
     * @param id
     * @param response
     * @throws IOException
     */
    @ResponseBody
    @GetMapping("/getSabbatical/{id}")
    public void getSabbaticalById(@PathVariable("id") Long id,
                                  HttpServletResponse response) throws IOException {
        SabbaticalApplicationInfo info = applicationService.getById(id);

        new InfoResponse(response, info);
    }

    /**
     * 相关部门通过调休id审批调休申请
     *
     * @param id
     * @param isAgree
     * @param session
     * @param response
     * @throws IOException
     */
    @ResponseBody
    @GetMapping("/confirmation/{id}/{isAgree}")
    public void sabbaticalConfirmation(@PathVariable("id") Long id,
                                       @PathVariable("isAgree") int isAgree,
                                       HttpSession session,
                                       HttpServletResponse response) throws IOException {
        MyTime myTime = new MyTime();
        Long userSerial = (Long) session.getAttribute("userSerial");
        SabbaticalConfirmationInfo confirmationInfo = confirmationService.getById(id);
        confirmationInfo.setIsAgree(isAgree);
        confirmationInfo.setUserSerial(userSerial);
        confirmationInfo.setApprovalDatetime(myTime.getNowTime());

        UpdateWrapper wrapper = new UpdateWrapper();
        wrapper.eq("id", id);

        boolean update = confirmationService.update(confirmationInfo, wrapper);

        new InfoResponse(response, update, "");
    }
}
