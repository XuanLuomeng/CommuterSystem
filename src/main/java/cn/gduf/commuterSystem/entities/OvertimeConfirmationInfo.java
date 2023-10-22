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
    private Long id;
    private Long overtimeId;
    private Long userSerial;
    private String userName;
    private Integer isAgree;
    private java.sql.Timestamp agreeTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getOvertimeId() {
        return overtimeId;
    }

    public void setOvertimeId(Long overtimeId) {
        this.overtimeId = overtimeId;
    }


    public Long getUserSerial() {
        return userSerial;
    }

    public void setUserSerial(Long userSerial) {
        this.userSerial = userSerial;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getIsAgree() {
        return isAgree;
    }

    public void setIsAgree(Integer isAgree) {
        this.isAgree = isAgree;
    }

    public Timestamp getAgreeTime() {
        return agreeTime;
    }

    public void setAgreeTime(Timestamp agreeTime) {
        this.agreeTime = agreeTime;
    }
}
