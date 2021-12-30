package org.suhyun.findhouse.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.suhyun.findhouse.dto.OptionDTO;
import org.suhyun.findhouse.repository.OptionRepository;

import java.util.stream.IntStream;

@SpringBootTest
public class OptionServiceTests {

    @Autowired
    private OptionService optionService;

    @Test
    public void registerTest(){

        IntStream.rangeClosed(1, 200).forEach(i->{

            OptionDTO optionDTO = OptionDTO.builder().airConditioner(true).bed(false).bookshelf(false).closet(true)
                    .desk(false).dishwasher(false).dryer(false).gasStove(false).induction(true).refrigerator(true).shoeRack(true)
                    .tv(false).sink(true).washer(true).build();

            optionService.register(optionDTO);

        });

    }
}
