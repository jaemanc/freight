package com.express.freight.jwt;

import com.express.freight.user.dto.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Base64;
import java.util.Date;

public class JwtTest {


//    public static void main(String[] args){
//        // 10년
//        Long tokenValidTime = 10*365*24*60*60*1000L;
//        String secretKey = "son_of_iksan";
//        Claims claims = Jwts.claims().setSubject("claims"); // JWT payload 에 저장되는 정보단위
//        // claims.put("roles", roles); // 정보는 key / value 쌍으로 저장된다.
//
//        claims.put("user_id", "manmans!");
//        claims.put("name","manmansName");
//        claims.put("contact","여기로연락줘~");
//
//        Date now = new Date();
//
//        String secretKeyBase64Encoded = Base64.getEncoder().encodeToString(secretKey.getBytes());
//
//        String jwt = Jwts.builder()
//                .setClaims(claims) // 정보 저장
//                .setIssuedAt(now) // 토큰 발행 시간 정보
//                .setExpiration(new Date(now.getTime() + tokenValidTime)) // set Expire Time
//                .signWith(SignatureAlgorithm.HS256, secretKeyBase64Encoded)  // 사용할 암호화 알고리즘과
//                // signature 에 들어갈 secret값 세팅
//                .compact();
//
//        System.out.println( jwt);
//    }


    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjbGFpbXMiLCJ1c2VyX2lkIjoibWFubWFucyEiLCJuYW1lIjoibWFubWFuc05hbWUiLCJjb250YWN0Ijoi7Jes6riw66Gc7Jew65297KSYfiIsImlhdCI6MTY5NTYyMjQ3OSwiZXhwIjoyMDEwOTgyNDc5fQ.PX1sBYgDJbsSENZxVW70rQaAdt0RB6R5IMvotY64PTM";
        String secretKey = "son_of_iksan";
        String temp = Jwts.parser()
                .setSigningKey(Base64.getEncoder()
                        .encodeToString(secretKey.getBytes()))
                .parseClaimsJws(token)
                .getBody()
                .get("name", String.class);

        System.out.println( temp + "@@@@@@@@@@");
    }





}
