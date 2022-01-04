package org.suhyun.findhouse.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.suhyun.findhouse.dto.OptionDTO;
import org.suhyun.findhouse.entity.Option;
import org.suhyun.findhouse.repository.OptionRepository;

import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@SpringBootTest
public class OptionServiceTests {

    @Autowired
    private OptionService service;


    @Test
    public void registerTest(){

        LongStream.rangeClosed(1, 200).forEach(i->{

            OptionDTO dto = OptionDTO.builder().airConditioner(true).bed(false).bookshelf(false).closet(true)
                    .desk(false).dishwasher(false).dryer(false).gasStove(false).induction(true).refrigerator(true).shoeRack(true)
                    .tv(false).sink(true).washer(true).houseNum(i).build();

            service.register(dto);
        });
    }


    @Test
    public void readTest(){

        OptionDTO entity = service.read(200L);

        System.out.println(entity);
    }


    @Test
    public void modifyTest(){

        OptionDTO dto = service.read(200L);

        Option entity = service.dtoToEntity(dto);

        entity.changeOptions(true,true,true,true,true,true,true,true,true,true,true,true,true,true);

        OptionDTO optionDto = service.entityToDto(entity);

        service.modify(optionDto);
    }


    @Test
    public void removeTest(){
        service.remove(199L);
    }
}
