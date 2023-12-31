package cn.gduf.commuterSystem.controller;

import cn.gduf.commuterSystem.entities.MyPage;
import cn.gduf.commuterSystem.entities.PersonalInfo;
import cn.gduf.commuterSystem.entities.UserInfo;
import cn.gduf.commuterSystem.service.PersonalInfoService;
import cn.gduf.commuterSystem.service.UserInfoService;
import cn.gduf.commuterSystem.utils.EncryptByMd5;
import cn.gduf.commuterSystem.utils.InfoResponse;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    public void signInUser(Long identity,
                           String userName,
                           Integer sex,
                           String telephone,
                           String email,
                           String address,
                           Long departmentSerial,
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
            userInfo.setIsDeleted(0);

            boolean a = userInfoService.save(userInfo);
            boolean b = personalInfoService.save(personalInfo);

            if (a == true && b == true) {
                new InfoResponse(response, true, "注册成功！");
            } else {
                new InfoResponse(response, true, "注册异常,请联系技术部工作人员！");
            }
        }
    }

    /**
     * 删除用户
     *
     * @param userSerial
     * @param response
     * @throws IOException
     */
    @ResponseBody
    @GetMapping("/DeletedUser/{userSerial}")
    public void deletedUser(@PathVariable("userSerial") Long userSerial,
                            HttpServletResponse response) throws IOException {
        UserInfo userInfo = userInfoService.selectUserInfoByUserSerial(userSerial);

        boolean deleted = userInfoService.deletedById(userInfo.getId());

        new InfoResponse(response, deleted, "");
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
    public void signUpUser(PersonalInfo personalInfo,
                           HttpSession session,
                           HttpServletResponse response) throws IOException {
        boolean checkResult = personalInfoService.checkPassword(personalInfo);

        PersonalInfo personalInfo1 = personalInfoService.lambdaQuery().like(PersonalInfo::getUserSerial, personalInfo.getUserSerial()).one();
        UserInfo userInfo = userInfoService.lambdaQuery().like(UserInfo::getUserSerial, personalInfo.getUserSerial()).one();

        if (checkResult) {
            //利用会话技术存储个人信息，以便用户访问其个人信息
            session.setAttribute("userSerial", personalInfo.getUserSerial());
            session.setAttribute("departmentSerial", userInfo.getDepartmentSerial());
            session.setAttribute("userPosition", userInfo.getUserPosition());
            session.setAttribute("userName", personalInfo1.getUserName());
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
    public void exit(HttpSession session) {
        session.invalidate();
    }

    /**
     * 通过账号查询用户信息（不包含密码和盐）
     *
     * @param session
     * @param response
     */
    @ResponseBody
    @GetMapping("/getPersonalInfo")
    public void getPersonalInfo(HttpSession session, HttpServletResponse response) throws IOException {
        Long userSerial = (Long) session.getAttribute("userSerial");

        if (userSerial != null) {

            PersonalInfo personalInfo = personalInfoService.selectPersonalInfoByUserSerial(userSerial);
            personalInfo.setPassword("");
            personalInfo.setSalt("");

            new InfoResponse(response, personalInfo);
        } else {
            new InfoResponse(response, false, "你还没有登录!");
        }
    }

    /**
     * 获取个人职位等信息
     *
     * @param session
     * @param response
     * @throws IOException
     */
    @ResponseBody
    @GetMapping("/getUserInfo")
    public void getUserInfo(HttpSession session, HttpServletResponse response) throws IOException {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserSerial((Long) session.getAttribute("userSerial"));
        userInfo.setDepartmentSerial((Long) session.getAttribute("departmentSerial"));
        userInfo.setUserPosition((String) session.getAttribute("userPosition"));
        userInfo.setUserName((String) session.getAttribute("userName"));

        new InfoResponse(response, userInfo);
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
    public void updatePassword(String oldPassword, String newPassword, HttpSession session, HttpServletResponse response) throws IOException {
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
            wrapper.eq(PersonalInfo::getUserSerial, userSerial);

            boolean update = personalInfoService.update(personal, wrapper);

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
    public void updateUserInfo(Long identity,
                               Long userSerial,
                               String userName,
                               Integer sex,
                               String telephone,
                               String email,
                               String address,
                               Long departmentSerial,
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

    /**
     * 通过分页获取基础个人信息(userName默认为@作为null值)
     *
     * @param response
     * @param pageNum
     * @throws IOException
     */
    @ResponseBody
    @GetMapping("/userInfoList/{pageNum}/{userName}")
    public void getUserInfoList(HttpServletResponse response,
                                @PathVariable("pageNum") int pageNum,
                                @PathVariable("userName") String userName) throws IOException {
        if (userName.equals("@")) {
            userName = null;
        }

        Page<UserInfo> page = new Page<>(pageNum, 20);

        IPage<UserInfo> iPage = userInfoService.selectUserInfos(page, userName);

        MyPage<UserInfo> myPage = new MyPage<>();
        myPage.setTotal(iPage.getTotal());
        myPage.setSize(iPage.getSize());
        myPage.setCurrent(iPage.getCurrent());
        myPage.setRecords(iPage.getRecords());

        new InfoResponse(response, myPage);
    }
}