package org.suhyun.findhouse.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.suhyun.findhouse.dto.HouseDTO;
import org.suhyun.findhouse.dto.PageRequestDTO;
import org.suhyun.findhouse.dto.PageResultDTO;
import org.suhyun.findhouse.entity.House;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class HouseServiceTests {

    @Autowired
    private HouseService service;


    @Test
    public void testRegister(){

        LocalDate date = LocalDate.of(2021,12,31);

        HouseDTO houseDTO = HouseDTO.builder()
                .completionDate(date)
                .title("사회 초년생이 살기 너무 좋은 집")
                .address("서울 ... ")
                .buildingType("오피스텔")
                .contractType("월세")
                .content("content...")
                .id("user")
                .moveInDate(date)
                .area(21)
                .brokerage(20)
                .minTerm(3)
                .loan(false)
                .parking(true)
                .pet(true)
                .elevator(true)
                .theFloor(3)
                .wholeFloor(10)
                .status("거래가능")
                .build();

        System.out.println(service.register(houseDTO));
    }

    @Test
    public void testList(){

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();

        PageResultDTO<HouseDTO, Object[]> resultDTO = service.getList(pageRequestDTO);

        for(HouseDTO houseDTO : resultDTO.getDtoList()){
            System.out.println(houseDTO);
        }

    }

    @Test
    public void testSearch(){

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .type("c")
                .keyword("내용")
                .build();


        PageResultDTO<HouseDTO, Object[]> resultDTO = service.getList(pageRequestDTO);

        System.out.println("prev : " + resultDTO.isPrev());
        System.out.println("next : " + resultDTO.isNext());
        System.out.println("total : " + resultDTO.getTotalPage());

        System.out.println("--------------------------------------------------------------");

        for(HouseDTO houseDTO : resultDTO.getDtoList()){
            System.out.println(houseDTO);
        }
        System.out.println("===================================================================");
        resultDTO.getPageList().forEach(i-> System.out.println(i));

    }
}
