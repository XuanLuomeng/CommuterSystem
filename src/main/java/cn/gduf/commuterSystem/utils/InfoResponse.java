package cn.gduf.commuterSystem.utils;

import cn.gduf.commuterSystem.entities.InfoResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Returns a result to the client
 */
public class InfoResponse {

    public InfoResponse() {
    }

    public InfoResponse(HttpServletResponse resp,Object obj) throws IOException {
        //将info对象序列化为json并将数据写回客户端
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(obj);
        //设置content-type防止乱码问题
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }

    public InfoResponse(HttpServletResponse resp, boolean flag, String errorMsg) throws IOException {
        InfoResult infoResult = new InfoResult();
        infoResult.setFlag(flag);
        infoResult.setErrorMsg(errorMsg);
        //将info对象序列化为json并将数据写回客户端
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(infoResult);
        //设置content-type防止乱码问题
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }
}