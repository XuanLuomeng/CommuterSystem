package cn.gduf.commuterSystem.controller;

import cn.gduf.commuterSystem.entities.SignInfo;
import cn.gduf.commuterSystem.entities.Time;
import cn.gduf.commuterSystem.service.SignInfoService;
import cn.gduf.commuterSystem.utils.InfoResponse;
import cn.gduf.commuterSystem.utils.MyTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author LuoXuanwei
 * @date 2023/10/18 15:21
 */
@RestController
@RequestMapping("/Sign")
public class SignController {
    @Resource
    private SignInfoService signInfoService;

    /**
     * 获取目前的签到状态
     *
     * @param userSerial
     * @param response
     * @throws IOException
     */
    @ResponseBody
    @GetMapping("/Status/{userSerial}")
    public void signStatus(@PathVariable("userSerial") Long userSerial,
                           HttpServletResponse response) throws IOException {
        List<SignInfo> signInfoList = signInfoService.getToDayCountByUserSerial(userSerial);

        if (signInfoList.size() == 0) {
            new InfoResponse(response, true, "今日还未签到");
        } else if (signInfoList.get(0).getSignOutTime() != null) {
            new InfoResponse(response, true, "已签退");
        }else {
            new InfoResponse(response,true,"工作中");
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
    public void sign(@PathVariable("userSerial") Long userSerial,
                     @PathVariable("InOrOut") String InOrOut,
                     HttpServletResponse response) throws IOException {
        MyTime nowTime = new MyTime();
        Timestamp ts = nowTime.getNowTime();

        boolean save = false;
        String errorMsg = "系统繁忙!";

        SignInfo signInfo = null;

        if (InOrOut.equals("In")) {
            signInfo = new SignInfo();
            signInfo.setUserSerial(userSerial);
            signInfo.setSignInTime(ts);

            save = signInfoService.save(signInfo);
            errorMsg = "签到:" + save;
        } else if (InOrOut.equals("Out")) {
            signInfo = signInfoService.getToDayCountByUserSerial(userSerial).get(0);
            signInfo.setSignOutTime(ts);

            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("id", signInfo.getId());

            save = signInfoService.update(signInfo, wrapper);
            errorMsg = "签退:" + save;
        }

        new InfoResponse(response, save, errorMsg);
    }

    /**
     * 根据员工编号获取指定范围内的签到情况
     *
     * @param userSerial
     * @param time
     * @param response
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("/Select/{userSerial}")
    public void selectBetweenStartAndEnd(@PathVariable("userSerial") Long userSerial,
                                         Time time,
                                         HttpServletResponse response) throws IOException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<SignInfo> list = signInfoService.lambdaQuery().
                like(SignInfo::getUserSerial, userSerial).
                orderByDesc(SignInfo::getSignInTime).
                between(SignInfo::getSignInTime, format.format(time.getStartTime()), format.format(time.getEndTime())).list();

        //将info对象序列化为json并将数据写回客户端
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(list);
        //设置content-type防止乱码问题
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
