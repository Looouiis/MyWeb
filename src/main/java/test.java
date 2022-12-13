import me.looouiiis.config.BaseConfig;
import me.looouiiis.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext IoC = new AnnotationConfigApplicationContext(BaseConfig.class);
        UserService bean = IoC.getBean(UserService.class);
//        boolean register = bean.register("123", "456", true, true);
//        System.out.println(register);
//        boolean close = bean.close("123", "456");
//        System.out.println(close);
        String login = bean.login("123", "45");
        System.out.println(login);
    }
}
