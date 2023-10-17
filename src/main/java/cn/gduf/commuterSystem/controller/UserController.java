package cn.gduf.commuterSystem.controller;

import cn.gduf.commuterSystem.entities.PersonalInfo;
import cn.gduf.commuterSystem.entities.UserInfo;
import cn.gduf.commuterSystem.service.PersonalInfoService;
import cn.gduf.commuterSystem.service.UserInfoService;
import cn.gduf.commuterSystem.utils.EncryptByMd5;
import cn.gduf.commuterSystem.utils.InfoResponse;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 19:04
 */
@RestController
@RequestMapping("/User")
public class UserController {
    @Resource
    private UserInfoService userInfoService;

    @Resource
    private PersonalInfoService personalInfoService;

    /**
     * 添加用户
     *
     * @param identity
     * @param userName
     * @param sex
     * @param telephone
     * @param email
     * @param address
     * @param departmentSerial
     * @param userPosition
     * @param response
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("/SignIn")
    public void SignInUser(long identity,
                           String userName,
                           long sex,
                           String telephone,
                           String email,
                           String address,
                           long departmentSerial,
                           String userPosition,
                           HttpServletResponse response) throws IOException {
        PersonalInfo personal = personalInfoService.lambdaQuery().like(PersonalInfo::getIdentity, identity).one();

        if (personal != null) {
            new InfoResponse(response, false, "用户已存在！");
        } else {
            //密码初始值为身份证后六位
            String password = (identity + "").substring(12, 18);
            //密码加密
            EncryptByMd5 encryptByMd5 = new EncryptByMd5(password);

            //员工id为入职年月日+随机3位数
            String dateSerial = "";
            while (true) {
                Date date = new Date();
                DateFormat dateFormat = new SimpleDateFormat("YYYYMMdd");
                Random random = new Random();
                dateSerial = dateFormat.format(date);
                for (int i = 0; i < 3; i++) {
                    dateSerial += (random.nextInt(10) + "");
                }
                if (personalInfoService.lambdaQuery().like(PersonalInfo::getUserSerial, dateSerial).one() == null) {
                    break;
                }
            }

            PersonalInfo personalInfo = new PersonalInfo();
            personalInfo.setUserSerial(Long.parseLong(dateSerial));
            personalInfo.setIdentity(identity);
            personalInfo.setPassword(encryptByMd5.getSimpleHash());
            personalInfo.setSalt(encryptByMd5.getSalt());
            personalInfo.setUserName(userName);
            personalInfo.setSex(sex);
            personalInfo.setTelephone(telephone);
            personalInfo.setEmail(email);
            personalInfo.setAddress(address);

            UserInfo userInfo = new UserInfo();
            userInfo.setUserSerial(Long.parseLong(dateSerial));
            userInfo.setUserPosition(userPosition);
            userInfo.setDepartmentSerial(departmentSerial);

            boolean a = userInfoService.saveOrUpdate(userInfo);
            boolean b = personalInfoService.saveOrUpdate(personalInfo);

            if (a == true && b == true) {
                new InfoResponse(response, true, "注册成功！");
            } else {
                new InfoResponse(response, true, "注册异常,请联系技术部工作人员！");
            }
        }
    }

    /**
     * 登录
     *
     * @param personalInfo
     * @param session
     * @param response
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("/SignUp")
    public void SignUpUser(PersonalInfo personalInfo,
                           HttpSession session,
                           HttpServletResponse response) throws IOException {
        boolean checkResult = personalInfoService.checkPassword(personalInfo);

        if (checkResult) {
            //利用会话技术存储个人信息，以便用户访问其个人信息
            session.setAttribute("userSerial", personalInfo.getUserSerial());
            new InfoResponse(response, true, "登陆成功");
        } else {
            new InfoResponse(response, false, "账号或密码有误！");
        }
    }

    /**
     * 退出登录，即删除客户端浏览器上的session
     *
     * @param session
     */
    @ResponseBody
    @GetMapping("/exitSignUp")
    public void Exit(HttpSession session) {
        session.invalidate();
    }

    /**
     * 通过账号查询用户信息（不包含密码和盐）
     *
     * @param session
     * @param response
     */
    @ResponseBody
    @GetMapping("/getUserInfo")
    public void GetUserInfo(HttpSession session, HttpServletResponse response) throws IOException {
        /**
         * 参数获取
         */
        Long userSerial = (Long) session.getAttribute("userSerial");

        PersonalInfo personalInfo = personalInfoService.selectPersonalInfoByUserSerial(userSerial);
        personalInfo.setPassword("");
        personalInfo.setSalt("");

        //将info对象序列化为json并将数据写回客户端
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(personalInfo);
        //设置content-type防止乱码问题
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    /**
     * 修改密码
     *
     * @param oldPassword
     * @param newPassword
     * @param session
     * @param response
     */
    @ResponseBody
    @PostMapping("/updatePassword")
    public void UpdatePassword(String oldPassword, String newPassword, HttpSession session, HttpServletResponse response) throws IOException {
        /**
         * 获取参数
         */
        Long userSerial = (Long) session.getAttribute("userSerial");

        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setUserSerial(userSerial);
        personalInfo.setPassword(oldPassword);

        boolean result = personalInfoService.checkPassword(personalInfo);

        if (result) {
            EncryptByMd5 md5 = new EncryptByMd5(newPassword);
            PersonalInfo personal = new PersonalInfo();
            personal.setPassword(md5.getSimpleHash());
            personal.setSalt(md5.getSalt());

            LambdaQueryWrapper<PersonalInfo> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(PersonalInfo::getUserSerial, userSerial).setEntity(personal);
            boolean update = personalInfoService.update(wrapper);

            if (update) {
                new InfoResponse(response, true, "修改成功");
            } else {
                new InfoResponse(response, false, "系统繁忙,请重试！");
            }
        }
        new InfoResponse(response, false, "修改失败，旧密码不正确");
    }

    /**
     * 修改个人信息
     *
     * @param identity
     * @param userName
     * @param sex
     * @param telephone
     * @param email
     * @param address
     * @param departmentSerial
     * @param userPosition
     * @param response
     */
    @ResponseBody
    @PostMapping("/updateUserInfos")
    public void UpdateUserInfo(long identity,
                               Long userSerial,
                               String userName,
                               long sex,
                               String telephone,
                               String email,
                               String address,
                               long departmentSerial,
                               String userPosition,
                               HttpServletResponse response) throws IOException {

        PersonalInfo personal = personalInfoService.selectPersonalInfoByUserSerial(userSerial);
        UserInfo userInfo = userInfoService.selectUserInfoByUserSerial(userSerial);

        if (personal == null) {
            new InfoResponse(response, false, "用户不存在！");
        } else {
            personal.setIdentity(identity);
            personal.setUserName(userName);
            personal.setSex(sex);
            personal.setTelephone(telephone);
            personal.setEmail(email);
            personal.setAddress(address);

            userInfo.setUserPosition(userPosition);
            userInfo.setDepartmentSerial(departmentSerial);

            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("user_serial", userSerial);

            boolean a = userInfoService.update(userInfo, wrapper);
            boolean b = personalInfoService.update(personal, wrapper);

            if (a == true && b == true) {
                new InfoResponse(response, true, "修改成功！");
            } else {
                new InfoResponse(response, true, "修改异常,请联系技术部工作人员！");
            }
        }
    }
}