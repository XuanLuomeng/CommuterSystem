package cn.gduf.commuterSystem.utils;

import cn.gduf.commuterSystem.entities.InfoResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 返回前端信息工具类
 *
 * @author LuoXuanwei
 * @date 2023/10/14 18:35
 */
public class InfoResponse {
    public InfoResponse() {
    }

    public InfoResponse(HttpServletResponse resp, Object obj) throws IOException {
        this.Response(resp, obj);
    }

    public InfoResponse(HttpServletResponse resp, boolean flag, String errorMsg) throws IOException {
        InfoResult infoResult = new InfoResult();
        infoResult.setFlag(flag);
        infoResult.setErrorMsg(errorMsg);
        this.Response(resp, infoResult);
    }

    public void Response(HttpServletResponse resp, Object data) throws IOException {
        //将info对象序列化为json并将数据写回客户端
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(data);
        //设置content-type防止乱码问题
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }
}