package me.looouiiis.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenOperator {
    static private String secret = "LooouiiisWebLooouiiisWebLooouiiisWeb";
    public static String generate(int id, int day, boolean isMe){
        Calendar date = Calendar.getInstance();
        Date iat = date.getTime();
        date.add(Calendar.DAY_OF_MONTH, day);
        Date exp = date.getTime();
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        Map<String,Object> header = new HashMap<>();
        header.put("typ","JWT");
        String token = Jwts.builder()
                .setHeader(header)
                .claim("id",id)
                .claim("ano", false)
                .claim("p", isMe)
                .setIssuedAt(iat)
                .setExpiration(exp)
                .signWith(key)
                .compact();
        return token;
    }
    public static String generateAno(int id){
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        Map<String,Object> header = new HashMap<>();
        header.put("typ","JWT");
        String token = Jwts.builder()
                .setHeader(header)
                .claim("id",id)
                .claim("ano", true)
                .signWith(key)
                .compact();
        return token;
    }
    public static HashMap<String, Object> verify(String token){
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
        return res;
    }
}
