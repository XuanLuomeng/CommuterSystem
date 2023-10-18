package cn.gduf.commuterSystem.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 15:17
 */
public class UserInfo {
    @TableId(type = IdType.ASSIGN_ID)
    private long id;
    private long userSerial;
    private long departmentSerial;
    private String userPosition;
    @TableLogic(value = "1", delval = "0")
    private Integer isDeleted;


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

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

}
