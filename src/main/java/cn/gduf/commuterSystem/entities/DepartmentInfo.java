package cn.gduf.commuterSystem.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 15:17
 */
public class DepartmentInfo {
    @TableId(type = IdType.ASSIGN_ID)
    private long id;
    private long departmentSerial;
    private String departmentName;
    private String departmentManger;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getDepartmentSerial() {
        return departmentSerial;
    }

    public void setDepartmentSerial(long departmentSerial) {
        this.departmentSerial = departmentSerial;
    }


    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }


    public String getDepartmentManger() {
        return departmentManger;
    }

    public void setDepartmentManger(String departmentManger) {
        this.departmentManger = departmentManger;
    }

}
