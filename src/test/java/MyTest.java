import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author LuoXuanwei
 * @date 2023/10/17 15:56
 */
public class MyTest {
    @Test
    public void testDate() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dateFormat.format(date);
        Timestamp ts = Timestamp.valueOf(format);
        System.out.println(ts);
    }
}
