package com.express.freight.util;

import com.express.freight.user.UserService;
import com.express.freight.user.dto.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;


public class JWTUtil {

    // 10년
    private static Long tokenValidTime = 10*365*24*60*60*1000L;
    private static String secretKey = "son_of_iksan";
    public String createToken(UserDto userDto) {
        Claims claims = Jwts.claims().setSubject(userDto.getUserId()); // JWT payload 에 저장되는 정보단위
        // claims.put("roles", roles); // 정보는 key / value 쌍으로 저장된다.

        claims.put("user_id", userDto.getUserId());
        claims.put("name",userDto.getName());
        claims.put("contact",userDto.getContact());

        Date now = new Date();

        String jwt = Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + tokenValidTime)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())  // 사용할 암호화 알고리즘과
                // signature 에 들어갈 secret값 세팅
                .compact();

        return jwt;
    }

    // 토큰에서 회원 아이디 추출
    public static String getUserId(String token) {
        return Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes())).parseClaimsJws(token).getBody().getSubject();
    }

    // name
    public static String getUserName(String token) {
        Object name = Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes())).parseClaimsJws(token).getBody().get("name");
        String userName = String.valueOf(name);
        return userName;
    }
    // contact
    public static String getUserContact(String token) {
        Object id = Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes())).parseClaimsJws(token).getBody().get("contact");
        String userContact = String.valueOf(id);
        return userContact;
    }

    // 토큰의 유효성 검증
    public static boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes())).parseClaimsJws(jwtToken);
            String userId = getUserId(jwtToken);

            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
