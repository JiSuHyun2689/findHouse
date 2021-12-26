package org.suhyun.findhouse.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.suhyun.findhouse.entity.House;
import org.suhyun.findhouse.entity.HouseImage;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
public class HouseRepositoryTests {


    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private HouseImageRepository houseImageRepository;

    @Test
    public void houseInsertTest(){

        LocalDate date = LocalDate.of(2021, 12, 31);

        IntStream.rangeClosed(1, 10).forEach(i ->{
            House house = House.builder().completionDate(date).address("서울 ... " + i ).buildingType(1).contractType(2).content("content..." + i).id("user" + i).moveInDate(date).area(21).brokerage(20).minTerm(3)
                    .loan(false).parking(true).pet(true).elevator(true).theFloor(3).wholeFloor(10).status("거래가능").build();

            houseRepository.save(house);

            int random = (int)(Math.random() * 5) + 1;

            for(int j=0; j<random; j++){

                HouseImage houseImage = HouseImage.builder().uuid(UUID.randomUUID().toString()).house(house).imageName("imgName..." + i).path("path..."+i).build();

                houseImageRepository.save(houseImage);
            }
        });
    }

    @Test
    @Transactional
    @Commit
    public void houseUpdateTest(){

        House house = houseRepository.getById(1L);

        house.changeStatus("변경");
        house.changeContent("내용 변경");
        houseRepository.save(house);

    }

    @Test
    public void houseGetTest(){

        Optional<House> result = houseRepository.findById(20L);

        House house = result.get();

        System.out.println(house);
    }

    @Test
    @Transactional
    @Commit
    public void houseDeleteTest(){

        Long houseNum = 20L;
        HouseImage houseImage = HouseImage.builder().house(House.builder().houseNum(houseNum).build()).build();
        System.out.println(houseImage);

        //houseImageRepository.deletebyHouse(houseImage);

        //houseRepository.deleteById(houseNum);

    }
}
