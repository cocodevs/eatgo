package com.dadongs.eatgo.utils;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

class JwtUtilTest {

    private static final String SECRET = "abcdefghijklnmopqrstuvwxyz123456";
    
    private JwtUtil jwtUtil;
    
    @BeforeEach
    public void setUp() {
        jwtUtil = new JwtUtil(SECRET);
    }
    
    @Test
    public void createToken() {        
        
        String token = jwtUtil.createToken(1L, "cocodev", null);
        System.out.println("token : " + token);
        assertThat(token, containsString("."));

        String token2 = jwtUtil.createToken(1L, "cocodev", 1004L);
        System.out.println("token : " + token2);
        assertThat(token2, containsString("."));
    }
    
    @Test
    public void getClaims() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEsIm5hbWUiOiJjb2NvZGV2In0.g7BJHpONXgWRe-YCBurhTLvB_hG30V45I1Z9TW8qhxs";

        Claims claims = jwtUtil.getClaims(token);

        assertThat(claims.get("userId", Long.class), is(1L));
        assertThat(claims.get("name"), is("cocodev"));
    }
}