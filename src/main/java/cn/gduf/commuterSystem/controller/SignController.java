package cn.gduf.commuterSystem.controller;

import cn.gduf.commuterSystem.entities.SignInInfo;
import cn.gduf.commuterSystem.entities.SignOutInfo;
import cn.gduf.commuterSystem.service.SignInInfoService;
import cn.gduf.commuterSystem.service.SignOutInfoService;
import cn.gduf.commuterSystem.utils.InfoResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author LuoXuanwei
 * @date 2023/10/18 15:21
 */
@RestController
@RequestMapping("/Sign")
public class SignController {
    @Resource
    private SignInInfoService signInInfoService;

    @Resource
    private SignOutInfoService signOutInfoService;

    /**
     * 获取目前的签到状态
     *
     * @param userSerial
     * @param response
     * @throws IOException
     */
    @ResponseBody
    @GetMapping("/Status/{userSerial}")
    public void SignStatus(@PathVariable("userSerial") Long userSerial,
                           HttpServletResponse response) throws IOException {
        int signInCount = signInInfoService.getToDayCountByUserSerial(userSerial);
        int signOutCount = signOutInfoService.getToDayCountByUserSerial(userSerial);

        if (signInCount > signOutCount) {
            new InfoResponse(response, true, "工作中");
        } else if (signInCount == signOutCount) {
            new InfoResponse(response, true, "未工作中");
        } else {
            new InfoResponse(response, false, "签到系统错误，请联系技术部人员!");
        }
    }

    /**
     * 签到或签退
     *
     * @param userSerial
     * @param InOrOut
     * @param response
     * @throws IOException
     */
    @ResponseBody
    @GetMapping("/{InOrOut}/{userSerial}")
    public void SignIn(@PathVariable("userSerial") Long userSerial,
                       @PathVariable("InOrOut") String InOrOut,
                       HttpServletResponse response) throws IOException {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dateFormat.format(date);
        Timestamp ts = Timestamp.valueOf(format);

        boolean save = false;
        String errorMsg = "系统繁忙!";

        if (InOrOut.equals("In")) {
            SignInInfo signInInfo = new SignInInfo();
            signInInfo.setUserSerial(userSerial);
            signInInfo.setSignInTime(ts);

            save = signInInfoService.save(signInInfo);
            errorMsg = "签到成功";
        } else if (InOrOut.equals("Out")) {
            SignOutInfo signOutInfo = new SignOutInfo();
            signOutInfo.setUserSerial(userSerial);
            signOutInfo.setSignOutTime(ts);

            save = signOutInfoService.save(signOutInfo);
            errorMsg = "签退成功";
        }

        new InfoResponse(response, save, errorMsg);
    }
}
