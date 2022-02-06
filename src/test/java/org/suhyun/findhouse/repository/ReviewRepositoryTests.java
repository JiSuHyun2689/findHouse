package org.suhyun.findhouse.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.suhyun.findhouse.entity.House;
import org.suhyun.findhouse.entity.Member;
import org.suhyun.findhouse.entity.Review;

import java.util.stream.IntStream;

@SpringBootTest
public class ReviewRepositoryTests {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertReviewTests(){

        IntStream.rangeClosed(1, 200).forEach(i->{

            Long houseNum = (long)(Math.random()*20) + 1;

            Long randomNum = ((long)(Math.random()*20) + 1);

            // 평가자 ID
            Member member = Member.builder().id("user"+i).build();

            Review review = Review.builder()
                    .member(member)
                    .targetId("user"+randomNum)
                    .house(House.builder().houseNum(houseNum).build())
                    .grade((int)(Math.random()*5) + 1)
                    .content("이 중개인 평점..")
                    .build();

            System.out.println(review);

            reviewRepository.save(review);
        });
    }
}
