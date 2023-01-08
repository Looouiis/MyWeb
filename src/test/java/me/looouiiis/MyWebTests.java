package me.looouiiis;

import com.alibaba.fastjson2.JSON;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import me.looouiiis.controller.Message;
import me.looouiiis.dao.HomeDao;
import me.looouiiis.dao.MessageDao;
import me.looouiiis.pojo.Header;
import me.looouiiis.pojo.Home;
import me.looouiiis.service.HeaderService;
import me.looouiiis.service.HomeService;
import me.looouiiis.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class MyWebTests {
    @Autowired
    private HomeService homeService;
    @Test
    void contextLoads() {
        Home home = new Home();
//        home.setId(1);
        home.setContents(new String[]{"123","321"});
        home.setHeader("lalala");
        home.setId(7);
        homeService.updateSingle(home);
        System.out.println("haha"+home.getId());
    }

}
