package org.suhyun.findhouse.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.suhyun.findhouse.entity.Member;

import java.time.LocalDate;
import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertMembers(){

        IntStream.rangeClosed(1, 200).forEach(i->{

            Member member = Member.builder()
                    .id("user"+i).userName("이름"+i)
                    .nickName("헤헤헤"+i)
                    .contract(0102222333+i).password("1111")
                    .birth(LocalDate.of(1996,1,29))
                    .fromSocial(false).build();

            memberRepository.save(member);
        });
    }
}
