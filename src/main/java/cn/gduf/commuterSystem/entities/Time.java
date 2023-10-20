package cn.gduf.commuterSystem.entities;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author LuoXuanwei
 * @date 2023/10/18 18:25
 */
public class Time {
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
