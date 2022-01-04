package org.suhyun.findhouse.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.suhyun.findhouse.dto.PriceDTO;
import org.suhyun.findhouse.dto.StructureDTO;
import org.suhyun.findhouse.entity.Price;
import org.suhyun.findhouse.entity.Structure;

import java.util.stream.LongStream;

@SpringBootTest
public class PriceServiceTests {

    @Autowired
    private PriceService service;


    @Test
    public void registerTest(){

        LongStream.rangeClosed(1, 200).forEach(i->{

            PriceDTO dto = PriceDTO.builder().houseNum(i).monthly(500000).deposit(5000000).price(0).build();

            service.register(dto);
        });
    }


    @Test
    public void readTest(){

        PriceDTO dto = service.read(200L);

        System.out.println(dto);
    }


    @Test
    public void modifyTest(){

        PriceDTO dto = service.read(200L);

        Price entity = service.dtoToEntity(dto);

        entity.changePrices(1,6000000,600000);

        PriceDTO priceDto = service.entityToDto(entity);

        service.modify(priceDto);
    }


    @Test
    public void removeTest(){
        service.remove(199L);
    }
}