package org.suhyun.findhouse.service;

import org.suhyun.findhouse.dto.CostDTO;
import org.suhyun.findhouse.dto.PageRequestDTO;
import org.suhyun.findhouse.dto.PageResultDTO;
import org.suhyun.findhouse.dto.PriceDTO;
import org.suhyun.findhouse.entity.Cost;
import org.suhyun.findhouse.entity.House;
import org.suhyun.findhouse.entity.Price;

public interface PriceService {

    Long register(PriceDTO dto);

    public PageResultDTO<PriceDTO, Price> getList(PageRequestDTO requestDTO);

    PriceDTO read(Long houseNum);

    void modify(PriceDTO dto);

    void remove(Long houseNum);

    default Price dtoToEntity(PriceDTO dto){
        Price entity = Price.builder().price(dto.getPrice()).house(House.builder().houseNum(dto.getHouseNum()).build()).priceNum(dto.getPriceNum())
                .monthly(dto.getMonthly()).deposit(dto.getDeposit()).build();
        return entity;
    }

    default PriceDTO entityToDto(Price entity){
        PriceDTO dto = PriceDTO.builder().price(entity.getPrice()).houseNum(entity.getHouse().getHouseNum()).priceNum(entity.getPriceNum())
                .monthly(entity.getMonthly()).deposit(entity.getDeposit()).build();
        return dto;
    }
}
