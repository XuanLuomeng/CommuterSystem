package cn.gduf.commuterSystem.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 15:17
 */
public class OvertimeApplicationInfo {
  @TableId(type = IdType.ASSIGN_ID)
  private long id;
  private long userSerial;
  private java.sql.Timestamp startDatetime;
  private java.sql.Timestamp endDatetime;


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


  public java.sql.Timestamp getStartDatetime() {
    return startDatetime;
  }

  public void setStartDatetime(java.sql.Timestamp startDatetime) {
    this.startDatetime = startDatetime;
  }


  public java.sql.Timestamp getEndDatetime() {
    return endDatetime;
  }

  public void setEndDatetime(java.sql.Timestamp endDatetime) {
    this.endDatetime = endDatetime;
  }

}
