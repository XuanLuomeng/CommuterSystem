import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author LuoXuanwei
 * @date 2023/10/17 15:56
 */
public class MyTest {
    @Test
    public void testDate() {
        Random random = new Random();
        String x = "20171216";
        for (int i = 0; i < 3; i++) {
            x += (random.nextInt(10) + "");
        }
        System.out.println(Long.parseLong(x));
    }
}
