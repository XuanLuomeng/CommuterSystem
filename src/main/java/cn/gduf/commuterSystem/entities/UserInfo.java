package cn.gduf.commuterSystem.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 15:17
 */
public class UserInfo {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private Long userSerial;
    @TableField(exist = false)
    private String userName;
    private Long departmentSerial;
    private String userPosition;
    private Integer isDeleted;


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


    public Long getDepartmentSerial() {
        return departmentSerial;
    }

    public void setDepartmentSerial(Long departmentSerial) {
        this.departmentSerial = departmentSerial;
    }


    public String getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
    }


    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

}
