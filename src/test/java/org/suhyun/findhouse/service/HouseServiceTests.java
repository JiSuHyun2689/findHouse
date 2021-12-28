package org.suhyun.findhouse.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.suhyun.findhouse.dto.HouseDTO;
import org.suhyun.findhouse.dto.PageRequestDTO;
import org.suhyun.findhouse.dto.PageResultDTO;
import org.suhyun.findhouse.entity.House;

import java.time.LocalDate;

@SpringBootTest
public class HouseServiceTests {

    @Autowired
    private HouseService houseService;


    @Test
    public void testRegister(){

        LocalDate date = LocalDate.of(2021, 12, 31);

        HouseDTO houseDTO = HouseDTO.builder().completionDate(date).title("사회 초년생이 살기 너무 좋은 집").address("서울 ... ").buildingType("오피스텔").contractType("월세").content("content...").id("user").moveInDate(date).area(21).brokerage(20).minTerm(3)
                .loan(false).parking(true).pet(true).elevator(true).theFloor(3).wholeFloor(10).status("거래가능").build();

        System.out.println(houseService.register(houseDTO));
    }

    @Test
    public void testList(){

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();

        PageResultDTO<HouseDTO, House> resultDTO = houseService.getList(pageRequestDTO);

        for(HouseDTO houseDTO : resultDTO.getDtoList()){
            System.out.println(houseDTO);
        }

    }
}
