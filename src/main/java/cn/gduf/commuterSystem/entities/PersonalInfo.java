package cn.gduf.commuterSystem.entities;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 15:17
 */
public class PersonalInfo {

    private long id;
    private long userSerial;
    private long identity;
    private String password;
    private String salt;
    private String userName;
    private long sex;
    private String telephone;
    private String email;
    private String address;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdentity() {
        return identity;
    }

    public void setIdentity(long identity) {
        this.identity = identity;
    }

    public long getUserSerial() {
        return userSerial;
    }

    public void setUserSerial(long userSerial) {
        this.userSerial = userSerial;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public long getSex() {
        return sex;
    }

    public void setSex(long sex) {
        this.sex = sex;
    }


    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
