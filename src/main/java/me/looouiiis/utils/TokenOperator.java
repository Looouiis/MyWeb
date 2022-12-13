package me.looouiiis.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class TokenOperator {
    static private String secret = "LooouiiisWebLooouiiisWebLooouiiisWeb";
    public static String generate(String username){
        Calendar date = Calendar.getInstance();
        Date iat = date.getTime();
        date.add(Calendar.DAY_OF_MONTH, 30);
        Date exp = date.getTime();
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        Map<String,Object> header = new HashMap<>();
        header.put("typ","JWT");
        String token = Jwts.builder()
                .setHeader(header)
                .claim("username",username)
                .setIssuedAt(iat)
                .setExpiration(exp)
                .signWith(key)
                .compact();
        return token;
    }
    public static HashMap<String, Object> verify(String token){
        Jws<Claims> jws;
        HashMap<String, Object> res = new HashMap<>();
        res.put("trusted",true);
        try{
            SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
            jws = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            res.put("jws",jws.getBody());
        }
        catch (JwtException e){
            res.put("trusted",false);
        }
        return res;
    }
}
