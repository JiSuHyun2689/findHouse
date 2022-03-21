package org.suhyun.findhouse.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.suhyun.findhouse.entity.Member;

import java.time.LocalDate;
import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository repository;

    @Test
    public void insertMembers() {

        IntStream.rangeClosed(1, 200).forEach(i -> {

            Member member = Member.builder()
                    .id("user" + i).userName("이름" + i)
                    .nickName("헤헤헤" + i)
                    .contact(0102222333 + i).password("1111")
                    .birth(LocalDate.of(1996, 1, 29))
                    .fromSocial(false).build();

            repository.save(member);
        });
    }


    @Test
    @Transactional(readOnly = true)
    public void readTest() {

        Member entity = repository.getById("user1");

        System.out.println(entity);

    }


    @Test
    public void updateTest() {

        Member entity = Member.builder().id("user1")
                .password("2222")
                .userName("변경테스트")
                .fromSocial(true)
                .contact(1234567)
                .nickName("변경할거지롱")
                .birth(LocalDate.now())
                .build();

        repository.save(entity);

    }


    @Test
    public void deleteTest() {
        repository.deleteById("user2");
    }

}
