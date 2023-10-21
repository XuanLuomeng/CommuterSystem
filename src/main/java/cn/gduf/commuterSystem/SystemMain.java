package cn.gduf.commuterSystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author LuoXuanwei
 * @date 2023/10/14 15:44
 */
@SpringBootApplication
@MapperScan("cn.gduf.commuterSystem.mapper")
public class SystemMain {
    public static void main(String[] args) {
        String str = "1,";
        System.out.println(str.split(",").length);
    }
}
