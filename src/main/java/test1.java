import me.looouiiis.config.BaseConfig;
import me.looouiiis.pojo.AnonymousMessage;
import me.looouiiis.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test1 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext IoC = new AnnotationConfigApplicationContext(BaseConfig.class);
        AnonymousMessage message = new AnonymousMessage();
        DateFormat df = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
        Date da = new Date();
        message.setDate(da);
        message.setContent("test");
        message.setAnoId(3);
        message.setLocal(true);
        UserService bean = IoC.getBean(UserService.class);
//        bean.commitAnoMessage(message);
    }
}
