package cn.gduf.commuterSystem.controller;

import cn.gduf.commuterSystem.entities.SchedulingInfo;
import cn.gduf.commuterSystem.service.SchedulingInfoService;
import cn.gduf.commuterSystem.utils.InfoResponse;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LuoXuanwei
 * @date 2023/10/21 10:07
 */
@RestController
@RequestMapping("/scheduling")
public class SchedulingController {
    @Resource
    private SchedulingInfoService schedulingInfoService;

    /**
     * 设置排班
     *
     * @param userSerial
     * @param dayString
     * @param response
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("/addScheduling/{userSerial}")
    public void addScheduling(@PathVariable("userSerial") Long userSerial,
                              String dayString,
                              HttpServletResponse response) throws IOException {
        String[] split = dayString.split(",");

        SchedulingInfo schedulingInfo = new SchedulingInfo();
        schedulingInfo.setUserSerial(userSerial);
        schedulingInfo.setYear(split[0].split("-")[0]);
        schedulingInfo.setMonth(split[0].split("-")[1]);
        String day = new String();
        for (int i = 0; i < split.length; i++) {
            day += split[i].split("-")[2] + ",";
        }
        schedulingInfo.setDay(day);

        boolean save = schedulingInfoService.save(schedulingInfo);

        new InfoResponse(response, save, "");
    }

    /**
     * 通过员工编号以及年月份获取详细排班信息
     *
     * @param userSerial
     * @param year
     * @param month
     * @param response
     * @throws IOException
     */
    @ResponseBody
    @GetMapping("/selectScheduling/{userSerial}/{year}/{month}")
    public void selectScheduling(@PathVariable("userSerial") Long userSerial,
                                 @PathVariable("year") String year,
                                 @PathVariable("month") String month,
                                 HttpServletResponse response) throws IOException {
        SchedulingInfo info = schedulingInfoService.lambdaQuery().like(SchedulingInfo::getUserSerial, userSerial).like(SchedulingInfo::getYear, year).like(SchedulingInfo::getMonth, month).one();
        String day = info.getDay();
        String[] split = day.split(",");
        String[] strs = new String[split.length + 1];
        strs[0] = String.valueOf(info.getId());
        for (int i = 0; i < split.length; i++) {
            split[i + 1] = info.getYear() + "-" + info.getMonth() + "-" + split[i];
        }

        new InfoResponse(response, split);
    }

    /**
     * 通过
     *
     * @param userSerial
     * @param id
     * @param dayString
     * @param response
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("/updateScheduling/{userSerial}")
    public void updateScheduling(@PathVariable("userSerial") long userSerial,
                                 Long id,
                                 String dayString,
                                 HttpServletResponse response) throws IOException {
        String[] split = dayString.split(",");

        SchedulingInfo schedulingInfo = new SchedulingInfo();
        schedulingInfo.setId(id);
        schedulingInfo.setUserSerial(userSerial);
        schedulingInfo.setYear(split[0].split("-")[0]);
        schedulingInfo.setMonth(split[0].split("-")[1]);
        String day = new String();
        for (int i = 0; i < split.length; i++) {
            day += split[i].split("-")[2] + ",";
        }
        schedulingInfo.setDay(day);

        UpdateWrapper wrapper = new UpdateWrapper();
        wrapper.eq("id", id);

        boolean update = schedulingInfoService.update(schedulingInfo, wrapper);

        new InfoResponse(response, update, "");
    }
}
