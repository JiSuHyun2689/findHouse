package org.suhyun.findhouse.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.suhyun.findhouse.dto.OptionDTO;
import org.suhyun.findhouse.dto.StructureDTO;
import org.suhyun.findhouse.entity.Option;
import org.suhyun.findhouse.entity.Structure;

import java.util.stream.LongStream;

@SpringBootTest
public class StructureServiceTests {

    @Autowired
    private StructureService service;


    @Test
    public void registerTest(){

        LongStream.rangeClosed(1, 200).forEach(i->{

            StructureDTO dto = StructureDTO.builder().houseNum(i).veranda(1).toilet(1).room(1).livingRoom(1).build();

            service.register(dto);
        });
    }


    @Test
    public void readTest(){

        StructureDTO dto = service.read(200L);

        System.out.println(dto);
    }


    @Test
    public void modifyTest(){

        StructureDTO dto = service.read(200L);

        Structure entity = service.dtoToEntity(dto);

        entity.chageStructures(2, 2, 2, 2);

        StructureDTO structureDto = service.entityToDto(entity);

        service.modify(structureDto);
    }


    @Test
    public void removeTest(){
        service.remove(199L);
    }
}