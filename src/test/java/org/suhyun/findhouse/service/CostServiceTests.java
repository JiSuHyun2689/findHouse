package org.suhyun.findhouse.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.suhyun.findhouse.dto.CostDTO;
import org.suhyun.findhouse.dto.OptionDTO;
import org.suhyun.findhouse.entity.Cost;
import org.suhyun.findhouse.entity.Option;
import org.suhyun.findhouse.repository.CostRepository;

import java.util.stream.LongStream;

@SpringBootTest
public class CostServiceTests {

    @Autowired
    private CostService service;


    @Test
    public void registerTest(){

        LongStream.rangeClosed(1, 200).forEach(i->{

            CostDTO dto = CostDTO.builder().water(true).costParking(false).costTv(false).totalCost(50000).internet(true).gas(false).electricity(false).etc(true).costContent("관리비 비용 내역").houseNum(i).build();

            service.register(dto);
        });
    }


    @Test
    public void readTest(){

        CostDTO dto = service.read(200L);

        System.out.println(dto);
    }


    @Test
    public void modifyTest(){

        CostDTO dto = service.read(200L);

        Cost entity = service.dtoToEntity(dto);

        entity.changeCosts(dto.getTotalCost(), dto.isElectricity(), dto.isGas(), dto.isWater(), dto.isCostTv(), dto.isInternet(), dto.isCostParking(), dto.isEtc());

        entity.changeContent("관리비 업데이트 업데이트!");

        CostDTO costDto = service.entityToDto(entity);

        service.modify(costDto);
    }


    @Test
    public void removeTest(){
        service.remove(199L);
    }
}
