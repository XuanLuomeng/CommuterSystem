package cn.gduf.commuterSystem.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 15:17
 */
public class SabbaticalApplicationInfo {
  @TableId(type = IdType.ASSIGN_ID)
  private long id;
  private long userSerial;
  private java.sql.Date startDate;
  private java.sql.Date endDate;
  private java.sql.Date targetStartDate;
  private java.sql.Date targetEndDate;


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


  public java.sql.Date getTargetStartDate() {
    return targetStartDate;
  }

  public void setTargetStartDate(java.sql.Date targetStartDate) {
    this.targetStartDate = targetStartDate;
  }


  public java.sql.Date getTargetEndDate() {
    return targetEndDate;
  }

  public void setTargetEndDate(java.sql.Date targetEndDate) {
    this.targetEndDate = targetEndDate;
  }

}
