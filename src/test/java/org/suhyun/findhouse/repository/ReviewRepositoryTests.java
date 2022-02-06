package org.suhyun.findhouse.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.suhyun.findhouse.entity.House;
import org.suhyun.findhouse.entity.Member;
import org.suhyun.findhouse.entity.Review;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class ReviewRepositoryTests {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MemberRepository memberRepository;



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

    @Transactional
    @Test
    public void testGetHouseReviews(){

        House house = House.builder().houseNum(20L).build();

        List<Review> result = reviewRepository.findByHouse(house);

        result.forEach(houseReview ->{
            System.out.println(houseReview.getHouse());
            System.out.println("\t"+houseReview.getGrade());
            System.out.println("\t"+houseReview.getContent());
            System.out.println("\t"+houseReview.getMember().getId());
            System.out.println("-------------------------------------------------------------------");
        });
    }




    @Transactional
    @Commit
    @Test
    public void testDeleteMember(){

        String id = "user200";

        Member member = Member.builder().id(id).build();

        reviewRepository.deleteByMember(member);

        memberRepository.deleteById(id);
    }

}
