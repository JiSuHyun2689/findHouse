package org.suhyun.findhouse.service;

import org.suhyun.findhouse.dto.HouseDTO;
import org.suhyun.findhouse.dto.PageRequestDTO;
import org.suhyun.findhouse.dto.PageResultDTO;
import org.suhyun.findhouse.dto.StructureDTO;
import org.suhyun.findhouse.entity.House;
import org.suhyun.findhouse.entity.Structure;

public interface StructureService {

    Long register(StructureDTO dto);

    public PageResultDTO<StructureDTO, Structure> getList(PageRequestDTO requestDTO);

    StructureDTO read(Long houseNum);

    void modify(StructureDTO dto);

    void remove(Long houseNum);

    default Structure dtoToEntity(StructureDTO dto){
        Structure entity = Structure.builder().livingRoom(dto.getLivingRoom()).room(dto.getRoom()).toilet(dto.getToilet()).veranda(dto.getVeranda())
                .structureNum(dto.getStructureNum()).house(House.builder().houseNum(dto.getHouseNum()).build()).build();
        return entity;
    }

    default StructureDTO entityToDto(Structure entity){
        StructureDTO dto = StructureDTO.builder().structureNum(entity.getStructureNum()).houseNum(entity.getStructureNum()).livingRoom(entity.getLivingRoom())
                .room(entity.getRoom()).toilet(entity.getToilet()).veranda(entity.getVeranda()).build();
        return dto;
    }
}
