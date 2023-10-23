package cn.gduf.commuterSystem.controller;

import cn.gduf.commuterSystem.entities.PersonalInfo;
import cn.gduf.commuterSystem.service.PersonalInfoService;
import cn.gduf.commuterSystem.utils.EncryptByMd5;
import cn.gduf.commuterSystem.utils.InfoResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LuoXuanwei
 * @date 2023/10/17 19:57
 */
@RestController
@RequestMapping("/Administrator")
public class AdministratorController {
    @Resource
    private PersonalInfoService personalInfoService;

    /**
     * 重置密码
     *
     * @param userSerial
     * @param response
     * @throws IOException
     */
    @ResponseBody
    @GetMapping("/resetPasswords/{userSerial}")
    public void resetPasswords(@PathVariable("userSerial") Long userSerial,
                               HttpServletResponse response) throws IOException {
        PersonalInfo personalInfo = personalInfoService.selectPersonalInfoByUserSerial(userSerial);

        //密码重置为身份证后六位
        String password = (personalInfo.getIdentity() + "").substring(12, 18);
        //密码加密
        EncryptByMd5 encryptByMd5 = new EncryptByMd5(password);

        personalInfo.setSalt(encryptByMd5.getSalt());
        personalInfo.setPassword(encryptByMd5.getSimpleHash());

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_serial", userSerial);

        boolean result = personalInfoService.update(personalInfo, wrapper);

        if (result) {
            new InfoResponse(response, true, "登陆成功");
        } else {
            new InfoResponse(response, false, "账号或密码有误！");
        }
    }
}
