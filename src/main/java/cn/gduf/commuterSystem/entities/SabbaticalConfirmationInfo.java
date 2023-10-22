package cn.gduf.commuterSystem.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 15:17
 */
public class SabbaticalConfirmationInfo {
  @TableId(type = IdType.ASSIGN_ID)
  private Long id;
  private Integer isAgree;
  private Long userSerial;
  private String userName;
  private java.sql.Timestamp approvalDatetime;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public Integer getIsAgree() {
    return isAgree;
  }

  public void setIsAgree(Integer isAgree) {
    this.isAgree = isAgree;
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

  public java.sql.Timestamp getApprovalDatetime() {
    return approvalDatetime;
  }

  public void setApprovalDatetime(java.sql.Timestamp approvalDatetime) {
    this.approvalDatetime = approvalDatetime;
  }

}
