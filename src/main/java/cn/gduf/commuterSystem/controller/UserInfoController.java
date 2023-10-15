package cn.gduf.commuterSystem.controller;

import cn.gduf.commuterSystem.entities.PersonalInfo;
import cn.gduf.commuterSystem.entities.UserInfo;
import cn.gduf.commuterSystem.service.PersonalInfoService;
import cn.gduf.commuterSystem.service.UserInfoService;
import cn.gduf.commuterSystem.utils.EncryptByMd5;
import cn.gduf.commuterSystem.utils.InfoResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 19:04
 */
@RestController
@RequestMapping("/UserInfo")
public class UserInfoController {
    @Resource
    private UserInfoService userInfoService;

    @Resource
    private PersonalInfoService personalInfoService;

    @PostMapping("/SignIn")
    public void SignInUser(long identity,
                           String userName,
                           long sex,
                           String telephone,
                           String email,
                           String address,
                           long departmentSerial,
                           String userPosition,
                           HttpSession session,
                           HttpServletResponse response) throws IOException {
        PersonalInfo personal = personalInfoService.lambdaQuery().like(PersonalInfo::getIdentity, identity).one();

        if (personal.getId() != 0) {
            new InfoResponse(response, false, "用户已存在！");
        } else {
            //密码初始值为身份证后六位
            String password = (identity+"").substring(12,18);
            //密码加密
            EncryptByMd5 encryptByMd5 = new EncryptByMd5(password);

            PersonalInfo personalInfo = new PersonalInfo();
            personalInfo.setIdentity(identity);
            personalInfo.setPassword(encryptByMd5.getSimpleHash());
            personalInfo.setSalt(encryptByMd5.getSalt());
            personalInfo.setUserName(userName);
            personalInfo.setSex(sex);
            personalInfo.setTelephone(telephone);
            personalInfo.setEmail(email);
            personalInfo.setAddress(address);

            userService.insertUser(user);
            int uid = userService.getUidByUserId(user.getUserId());
            checkPointService.insertCheckPointInfoByUid(uid);
            likeArticleService.insertLikeArray(uid, "");
            new InfoResponse(response, true, "注册成功！");
        }
    }
}
