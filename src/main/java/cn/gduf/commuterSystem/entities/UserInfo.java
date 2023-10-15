package cn.gduf.commuterSystem.entities;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 15:17
 */
public class UserInfo {

  private long id;
  private long userSerial;
  private long departmentSerial;
  private String userPosition;
  private long isDeleted;


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


  public long getDepartmentSerial() {
    return departmentSerial;
  }

  public void setDepartmentSerial(long departmentSerial) {
    this.departmentSerial = departmentSerial;
  }


  public String getUserPosition() {
    return userPosition;
  }

  public void setUserPosition(String userPosition) {
    this.userPosition = userPosition;
  }


  public long getIsDeleted() {
    return isDeleted;
  }

  public void setIsDeleted(long isDeleted) {
    this.isDeleted = isDeleted;
  }

}
