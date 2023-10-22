package cn.gduf.commuterSystem.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @author LuoXuanwei
 * @date 2023/10/21 10:01
 */
public class SchedulingInfo {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private Long userSerial;
    private String year;
    private String month;
    private String day;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserSerial() {
        return userSerial;
    }

    public void setUserSerial(Long userSerial) {
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
