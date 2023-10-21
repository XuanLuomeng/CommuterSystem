package cn.gduf.commuterSystem.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @author LuoXuanwei
 * @date 2023/10/21 10:01
 */
public class SchedulingInfo {
    @TableId(type = IdType.ASSIGN_ID)
    private long id;
    private long userSerial;
    private String year;
    private String month;
    private String day;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserSerial() {
        return userSerial;
    }

    public void setUserSerial(long userSerial) {
        this.userSerial = userSerial;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
