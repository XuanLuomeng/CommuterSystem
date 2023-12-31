package cn.gduf.commuterSystem.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 15:17
 */
public class LeaveApplicationInfo {
  @TableId(type = IdType.ASSIGN_ID)
  private Long id;
  private Long userSerial;
  private java.sql.Timestamp applicationDatetime;
  private java.sql.Date startDate;
  private java.sql.Date endDate;
  private String reason;


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


  public java.sql.Timestamp getApplicationDatetime() {
    return applicationDatetime;
  }

  public void setApplicationDatetime(java.sql.Timestamp applicationDatetime) {
    this.applicationDatetime = applicationDatetime;
  }


  public java.sql.Date getStartDate() {
    return startDate;
  }

  public void setStartDate(java.sql.Date startDate) {
    this.startDate = startDate;
  }


  public java.sql.Date getEndDate() {
    return endDate;
  }

  public void setEndDate(java.sql.Date endDate) {
    this.endDate = endDate;
  }


  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

}
