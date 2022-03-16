package org.suhyun.findhouse.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.suhyun.findhouse.entity.House;
import org.suhyun.findhouse.entity.Member;
import org.suhyun.findhouse.entity.Wish;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class WishRepositoryTests {

    @Autowired
    private WishRepository wishRepository;


    @Test
    public void insertWishTests() {

        IntStream.rangeClosed(1, 10).forEach(i -> {

            Long houseNum = (long) (Math.random() * 5) + 1;

            Member member = Member.builder().id("user1").build();

            Wish wish = Wish.builder()
                    .house(House.builder().houseNum(houseNum).build())
                    .member(member)
                    .build();


            System.out.println(wish);
            wishRepository.save(wish);
        });

    }


    @Test
    @Transactional
    public void testGetHouseWishs(){

        House house = House.builder().houseNum(4L).build();

        List<Wish> result = wishRepository.findByHouse(house);

        result.forEach(wish ->{
            System.out.println("\t"+wish.getWishNum());
            System.out.println(wish.getHouse());
            System.out.println("\t"+wish.getMember().getId());
            System.out.println("----------------------------------------------------");
        });
    }

    @Test
    @Transactional
    @Commit
    public void testDeleteByMember(){

        wishRepository.deleteByMember("user1", 1L);

    }

    @Test
    public void testDeleteByHouse(){

        wishRepository.deleteByHouse(1L);

    }

    @Test
    public void testDelete(){

        wishRepository.deleteById(1L);

    }


}
