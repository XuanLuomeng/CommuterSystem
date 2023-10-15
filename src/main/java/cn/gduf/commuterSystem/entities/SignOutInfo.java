package cn.gduf.commuterSystem.entities;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 15:17
 */
public class SignOutInfo {

  private long id;
  private long userSerial;
  private java.sql.Timestamp signOutTime;


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


  public java.sql.Timestamp getSignOutTime() {
    return signOutTime;
  }

  public void setSignOutTime(java.sql.Timestamp signOutTime) {
    this.signOutTime = signOutTime;
  }

}
