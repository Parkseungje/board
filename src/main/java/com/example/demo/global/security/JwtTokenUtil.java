package com.example.demo.global.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    private final String secretKey = "my-secret-key-123123"; // 또는 환경변수에서 가져오기

    // JWT Token 발급
    public String createToken(String loginId, long expireTimeMs) {
        Claims claims = Jwts.claims();
        claims.put("loginId", loginId);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // Claims에서 loginId 꺼내기
    public String getLoginId(String token) {
        return extractClaims(token).get("loginId").toString();
    }

    // 밝급된 Token이 만료 시간이 지났는지 체크
    public boolean isExpired(String token) {
        Date expiredDate = extractClaims(token).getExpiration();
        return expiredDate.before(new Date());
    }

    // SecretKey를 사용해 Token Parsing
    private Claims extractClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }
}