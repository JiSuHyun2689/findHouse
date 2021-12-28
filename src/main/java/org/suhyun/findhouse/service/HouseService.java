package org.suhyun.findhouse.service;

import org.suhyun.findhouse.dto.HouseDTO;
import org.suhyun.findhouse.dto.PageRequestDTO;
import org.suhyun.findhouse.dto.PageResultDTO;
import org.suhyun.findhouse.entity.House;

public interface HouseService {

    Long register(HouseDTO dto);

    public PageResultDTO<HouseDTO, House> getList(PageRequestDTO requestDTO);

    default House dtoToEntity(HouseDTO dto){
        House entity = House.builder().houseNum(dto.getHouseNum()).address(dto.getAddress()).area(dto.getArea()).brokerage(dto.getBrokerage())
                .buildingType(dto.getBuildingType()).contractType(dto.getContractType()).completionDate(dto.getCompletionDate())
                .content(dto.getContent()).elevator(dto.isElevator()).loan(dto.isLoan()).minTerm(dto.getMinTerm()).id(dto.getId())
                .moveInDate(dto.getMoveInDate()).parking(dto.isParking()).pet(dto.isPet()).status(dto.getStatus()).theFloor(dto.getTheFloor())
                .wholeFloor(dto.getWholeFloor()).view(dto.getView()).houseNum(dto.getHouseNum()).title(dto.getTitle()).build();
        return entity;
    }

    default HouseDTO entityToDto(House entity){
        HouseDTO dto = HouseDTO.builder().houseNum(entity.getHouseNum()).buildingType(entity.getBuildingType()).contractType(entity.getContractType())
                .address(entity.getAddress()).area(entity.getArea()).brokerage(entity.getBrokerage()).completionDate(entity.getCompletionDate())
                .content(entity.getContent()).elevator(entity.isElevator()).id(entity.getId()).loan(entity.isLoan()).minTerm(entity.getMinTerm())
                .pet(entity.isPet()).moveInDate(entity.getMoveInDate()).modDate(entity.getModDate()).parking(entity.isParking()).regDate(entity.getRegDate())
                .status(entity.getStatus()).theFloor(entity.getTheFloor()).wholeFloor(entity.getWholeFloor()).view(entity.getView()).title(entity.getTitle())
                .build();

        return dto;
    }
}
