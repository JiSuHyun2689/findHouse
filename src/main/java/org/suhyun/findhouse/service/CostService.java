package org.suhyun.findhouse.service;

import org.suhyun.findhouse.dto.CostDTO;
import org.suhyun.findhouse.dto.PageRequestDTO;
import org.suhyun.findhouse.dto.PageResultDTO;
import org.suhyun.findhouse.dto.StructureDTO;
import org.suhyun.findhouse.entity.Cost;
import org.suhyun.findhouse.entity.House;
import org.suhyun.findhouse.entity.Structure;

public interface CostService {

    Long register(CostDTO dto);

    public PageResultDTO<CostDTO, Cost> getList(PageRequestDTO requestDTO);

    CostDTO read(Long houseNum);

    void modify(CostDTO dto);

    void remove(Long houseNum);

    default Cost dtoToEntity(CostDTO dto){
        Cost entity = Cost.builder().costNum(dto.getCostNum()).content(dto.getCostContent()).totalCost(dto.getTotalCost()).electricity(dto.isElectricity())
                .etc(dto.isEtc()).gas(dto.isGas()).internet(dto.isInternet()).parking(dto.isCostParking()).tv(dto.isCostTv()).water(dto.isWater())
                .house(House.builder().houseNum(dto.getHouseNum()).build()).build();
        return entity;
    }

    default CostDTO entityToDto(Cost entity){
        CostDTO dto = CostDTO.builder().costNum(entity.getCostNum()).costContent(entity.getContent()).totalCost(entity.getTotalCost()).etc(entity.isEtc())
                .electricity(entity.isElectricity()).gas(entity.isGas()).houseNum(entity.getHouse().getHouseNum()).internet(entity.isInternet())
                .costTv(entity.isTv()).costParking(entity.isParking()).water(entity.isWater()).build();
        return dto;
    }
}
