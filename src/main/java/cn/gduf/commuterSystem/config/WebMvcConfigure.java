package cn.gduf.commuterSystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author LuoXuanwei
 * @date 2023/10/28 10:28
 */
@Configuration
public class WebMvcConfigure implements WebMvcConfigurer {
    /**
     * 处理前端静态文件页面路径问题
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
