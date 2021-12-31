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
    private OptionService optionService;

    @Test
    public void registerTest(){

        LongStream.rangeClosed(1, 200).forEach(i->{

            OptionDTO optionDTO = OptionDTO.builder().airConditioner(true).bed(false).bookshelf(false).closet(true)
                    .desk(false).dishwasher(false).dryer(false).gasStove(false).induction(true).refrigerator(true).shoeRack(true)
                    .tv(false).sink(true).washer(true).houseNum(i).build();

            optionService.register(optionDTO);
        });
    }


    @Test
    public void readTest(){

        OptionDTO option = optionService.read(200L);

        System.out.println(option);

    }

    @Test
    public void modifyTest(){

        OptionDTO dto = optionService.read(200L);

        Option entity = optionService.dtoToEntity(dto);

        entity.changeOptions(true,true,true,true,true,true,true,true,true,true,true,true,true,true);

        OptionDTO optionDTO = optionService.entityToDto(entity);

        optionService.modify(optionDTO);
    }

    @Test
    public void removeTest(){

        optionService.remove(199L);
    }
}
