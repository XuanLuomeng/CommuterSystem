package cn.gduf.commuterSystem.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.sql.Timestamp;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 15:17
 */
public class OvertimeConfirmationInfo {
    @TableId(type = IdType.ASSIGN_ID)
    private long id;
    private long overtimeId;
    private long userSerial;
    private String userName;
    private long isAgree;
    private java.sql.Timestamp agreeTime;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getOvertimeId() {
        return overtimeId;
    }

    public void setOvertimeId(long overtimeId) {
        this.overtimeId = overtimeId;
    }


    public long getUserSerial() {
        return userSerial;
    }

    public void setUserSerial(long userSerial) {
        this.userSerial = userSerial;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getIsAgree() {
        return isAgree;
    }

    public void setIsAgree(long isAgree) {
        this.isAgree = isAgree;
    }

    public Timestamp getAgreeTime() {
        return agreeTime;
    }

    public void setAgreeTime(Timestamp agreeTime) {
        this.agreeTime = agreeTime;
    }
}
