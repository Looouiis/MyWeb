import me.looouiiis.config.BaseConfig;
import me.looouiiis.dao.AccountDao;
import me.looouiiis.pojo.User;
import me.looouiiis.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext IoC = new AnnotationConfigApplicationContext(BaseConfig.class);
        AccountDao bean = IoC.getBean(AccountDao.class);
//        boolean register = bean.register("123", "456", true, true);
//        System.out.println(register);
//        boolean close = bean.close("123", "456");
//        System.out.println(close);
        User user = new User();
        List<User> login = bean.selectByUsername(null);
        System.out.println(login);
    }
}
