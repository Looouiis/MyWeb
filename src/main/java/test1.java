import me.looouiiis.config.SpringConfig;
import me.looouiiis.config.SpringMVCConfig;
import me.looouiiis.pojo.AnonymousMessage;
import me.looouiiis.service.AnnounceService;
import me.looouiiis.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test1 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext IoC = new AnnotationConfigApplicationContext(SpringConfig.class);
        System.out.println(IoC.getBean(AnnounceService.class));
//        bean.commitAnoMessage(message);
    }
}
