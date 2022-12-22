package me.looouiiis;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class MyWebTests {

    @Test
    void contextLoads() {
        int day = 3;
        int id = 1;
        String secret = "LooouiiisWebLooouiiisWebLooouiiisWeb";
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DAY_OF_MONTH, -10);
        Date iat = date.getTime();
        date.add(Calendar.DAY_OF_MONTH, day);
        Date exp = date.getTime();
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        Map<String,Object> header = new HashMap<>();
        header.put("typ","JWT");
        String token = Jwts.builder()
                .setHeader(header)
                .claim("id",id)
                .setIssuedAt(iat)
                .setExpiration(exp)
                .signWith(key)
                .compact();
        System.out.println(token);
    }

}
