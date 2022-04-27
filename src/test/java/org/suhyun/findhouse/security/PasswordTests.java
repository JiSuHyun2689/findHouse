package org.suhyun.findhouse.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.suhyun.findhouse.entity.Member;
import org.suhyun.findhouse.repository.MemberRepository;

import java.util.Optional;

@SpringBootTest
public class PasswordTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testEncode(){
        String password = "$2a$10$4UyqYEVdaVKV3CoBJOU.Ru2WRXlYGXp3c/DQW2W.S4zTySZ2i31GG";

        String enPw = passwordEncoder.encode(password);

        System.out.println("enPw : " + enPw);

        boolean matchResult = passwordEncoder.matches(password, enPw);

        System.out.println("matchResult : " + matchResult);
    }

}
