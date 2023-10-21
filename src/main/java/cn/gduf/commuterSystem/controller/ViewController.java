package cn.gduf.commuterSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author LuoXuanwei
 * @date 2023/10/21 16:18
 */
@Controller
public class ViewController {
    /**
     * html页面统一放在templates
     * 已经设置好了路径默认classpath:/templates/(这里的内容就是返回的字符串内容).html
     *
     * @return
     */
    @GetMapping("/index")
    public String XXView(){
        return "index";
    }
}
