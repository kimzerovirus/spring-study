package me.kzv.demo.security.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class JwtUtilTest {
    private JwtUtil jwtUtil;

    @BeforeEach
    public void testBefore(){
        jwtUtil = new JwtUtil();
    }

    @Test
    public void testEncode() throws Exception{
        String email = "user@aaa.com";
        String str = jwtUtil.generateToken(email);

        System.out.println(str);
    }

    @Test
    public void testValidate() throws Exception {
        String email = "user@aaa.com";
        String str = jwtUtil.generateToken(email);
        Thread.sleep(5000);
        String resultEmail = jwtUtil.validateAndExtract(str);

        System.out.println(resultEmail);
    }

}