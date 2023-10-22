package cn.gduf.commuterSystem.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 15:17
 */
public class DepartmentInfo {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private Long departmentSerial;
    private String departmentName;
    private String departmentManger;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getDepartmentSerial() {
        return departmentSerial;
    }

    public void setDepartmentSerial(Long departmentSerial) {
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
