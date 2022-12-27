package me.looouiiis;

import com.alibaba.fastjson2.JSON;
import io.jsonwebtoken.*;
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
        String secret = "LooouiiisWebLooouiiisWeb";
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MjEsImFubyI6dHJ1ZX0.fY48lZ6XyQgyEIUdGGsEU1ETC4Js4v98FNYH2UKLRXc";
        Jws<Claims> jws;
        HashMap<String, Object> res = new HashMap<>();
        res.put("trusted",true);
        res.put("outdated", false);
        try{
            SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
            jws = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            res.put("jws",jws.getBody());
        }
        catch (ExpiredJwtException e){
            res.put("jws",e.getClaims());
            res.put("outdated", true);
        }
        catch (JwtException e){
            e.printStackTrace();
            res.put("trusted",false);
        }
        return;
    }

}
