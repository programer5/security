package com.sp.fc.web;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.xml.bind.DatatypeConverter;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

public class JWTSimpleTest {

    private void printToken(String token) {
        String[] tokens = token.split("\\.");
        System.out.println("header: " + new String(Base64.getDecoder().decode(tokens[0])));
        System.out.println("body: " + new String(Base64.getDecoder().decode(tokens[1])));
    }

    @DisplayName("1. jjwt를 이용한 토큰 테스트")
    @Test
    void test_1() {
        String okta_token = Jwts.builder().addClaims(
                Map.of("name", "minseo", "price", 3000)
                ).signWith(SignatureAlgorithm.HS256, "minseo")
                .compact();
        System.out.println(okta_token);
        printToken(okta_token);
        System.out.println("okta_token = " + okta_token);

        Jws<Claims> tokenInfo = Jwts.parser().setSigningKey("minseo").parseClaimsJws(okta_token);
        System.out.println("tokenInfo = " + tokenInfo);
    }

    @DisplayName("2. java-jwt를 이용한 토큰 테스트")
    @Test
    void test_2() {

        byte[] SEC_KEY = DatatypeConverter.parseBase64Binary("minseo");
        System.out.println("SEC_KEY = " + SEC_KEY);

        String oauth0_token = JWT.create().withClaim("name", "minseo").withClaim("price", 3000)
                .sign(Algorithm.HMAC256(SEC_KEY));
        System.out.println("oauth0_token = " + oauth0_token);
        printToken(oauth0_token);

        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SEC_KEY)).build().verify(oauth0_token);
        System.out.println("verify = " + verify.getClaims());

        Jws<Claims> tokenInfo = Jwts.parser().setSigningKey(SEC_KEY).parseClaimsJws(oauth0_token);
        System.out.println("tokenInfo = " + tokenInfo);
    }

    @DisplayName("3. 만료 시간")
    @Test
    void test_3() throws InterruptedException {

        final Algorithm AL = Algorithm.HMAC256("minseo");
        String token = JWT.create().withSubject("a1234")
                .withNotBefore(new Date(System.currentTimeMillis() + 1000))
                .withExpiresAt(new Date(System.currentTimeMillis() + 3000))
                .sign(AL);

        try {
            DecodedJWT verify = JWT.require(AL).build().verify(token);
            System.out.println("verify.getClaims() = " + verify.getClaims());
        } catch (Exception ex) {
            System.out.println("유효하지 않은 토큰 입니다...");
            DecodedJWT decode = JWT.decode(token);
            System.out.println("decode = " + decode.getClaims());
        }
    }
}
