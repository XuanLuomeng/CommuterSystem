package cn.gduf.commuterSystem.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 15:17
 */
public class SabbaticalApplicationInfo {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private Long userSerial;
    private String date;
    private String targetDate;
    private java.sql.Timestamp applicationDatetime;


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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(String targetDate) {
        this.targetDate = targetDate;
    }

    public Timestamp getApplicationDatetime() {
        return applicationDatetime;
    }

    public void setApplicationDatetime(Timestamp applicationDatetime) {
        this.applicationDatetime = applicationDatetime;
    }
}
