package me.looouiiis;

import com.alibaba.fastjson2.JSON;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import me.looouiiis.controller.Message;
import me.looouiiis.dao.MessageDao;
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
    private Message messageService;
    @Test
    void contextLoads() {
        System.out.println(JSON.toJSONString(messageService.getAnoComment(1)));
    }

}
