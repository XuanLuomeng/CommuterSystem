package cn.gduf.commuterSystem.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author LuoXuanwei
 * @date 2023/10/18 18:38
 */
public class MyTime {
    private Timestamp nowTime;

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public MyTime() {
        Date date = new Date();
        String format = dateFormat.format(date);
        this.nowTime = Timestamp.valueOf(format);
    }

    public MyTime(Date date) {
        String format = dateFormat.format(date);
        this.nowTime = Timestamp.valueOf(format);
    }

    public void setNowTime(Date date) {
        String format = dateFormat.format(date);
        this.nowTime = Timestamp.valueOf(format);
    }

    public Timestamp getNowTime() {
        return nowTime;
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }
}
