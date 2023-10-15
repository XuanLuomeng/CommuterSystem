package cn.gduf.commuterSystem.entities;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 15:17
 */
public class SabbaticalConfirmationInfo {

  private long id;
  private long isAgree;
  private long userSerial;
  private java.sql.Timestamp approvalDatetime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getIsAgree() {
    return isAgree;
  }

  public void setIsAgree(long isAgree) {
    this.isAgree = isAgree;
  }


  public long getUserSerial() {
    return userSerial;
  }

  public void setUserSerial(long userSerial) {
    this.userSerial = userSerial;
  }


  public java.sql.Timestamp getApprovalDatetime() {
    return approvalDatetime;
  }

  public void setApprovalDatetime(java.sql.Timestamp approvalDatetime) {
    this.approvalDatetime = approvalDatetime;
  }

}
