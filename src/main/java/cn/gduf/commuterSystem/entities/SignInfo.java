package cn.gduf.commuterSystem.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.sql.Timestamp;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 15:17
 */
public class SignInfo {
  @TableId(type = IdType.ASSIGN_ID)
  private Long id;
  private Long userSerial;
  private java.sql.Timestamp signInTime;
  private java.sql.Timestamp signOutTime;


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


  public java.sql.Timestamp getSignInTime() {
    return signInTime;
  }

  public void setSignInTime(java.sql.Timestamp signInTime) {
    this.signInTime = signInTime;
  }

  public Timestamp getSignOutTime() {
    return signOutTime;
  }

  public void setSignOutTime(Timestamp signOutTime) {
    this.signOutTime = signOutTime;
  }
}
