import me.looouiiis.config.SpringConfig;
import me.looouiiis.config.SpringMVCConfig;
import me.looouiiis.dao.AccountDao;
import me.looouiiis.pojo.AnonymousMessage;
import me.looouiiis.pojo.UserForUpdate;
import me.looouiiis.service.AnnounceService;
import me.looouiiis.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test1 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext IoC = new AnnotationConfigApplicationContext(SpringConfig.class);
        AccountDao bean = IoC.getBean(AccountDao.class);
        UserForUpdate user = new UserForUpdate();
        user.setId(18);
        user.setOriPassword("123");
        System.out.println(bean.update(user));
    }
}
