package org.suhyun.findhouse.service;

import org.suhyun.findhouse.dto.OptionDTO;
import org.suhyun.findhouse.dto.PageRequestDTO;
import org.suhyun.findhouse.dto.PageResultDTO;
import org.suhyun.findhouse.entity.House;
import org.suhyun.findhouse.entity.Option;

public interface OptionService {


    Long register(OptionDTO dto);

    public PageResultDTO<OptionDTO, Option> getList(PageRequestDTO requestDTO);

    OptionDTO read(Long houseNum);

    void modify(OptionDTO dto);

    void remove(Long houseNum);

    default Option dtoToEntity(OptionDTO dto){
        Option entity = Option.builder().optionNum(dto.getOptionNum()).bed(dto.isBed()).airConditioner(dto.isAirConditioner())
                .bookshelf(dto.isBookshelf()).closet(dto.isCloset()).desk(dto.isDesk()).dishwasher(dto.isDishwasher())
                .dryer(dto.isDryer()).gasStove(dto.isGasStove()).induction(dto.isInduction()).refrigerator(dto.isRefrigerator())
                .shoeRack(dto.isShoeRack()).sink(dto.isSink()).tv(dto.isTv()).washer(dto.isWasher()).house(House.builder().houseNum(dto.getHouseNum()).build()).build();
        return entity;
    }

    default OptionDTO entityToDto(Option entity){
        OptionDTO dto = OptionDTO.builder().optionNum(entity.getOptionNum()).bed(entity.isBed()).airConditioner(entity.isAirConditioner())
                .bookshelf(entity.isBookshelf()).desk(entity.isDesk()).closet(entity.isCloset()).dishwasher(entity.isDishwasher())
                .dryer(entity.isDryer()).gasStove(entity.isGasStove()).induction(entity.isInduction()).refrigerator(entity.isRefrigerator())
                .sink(entity.isSink()).shoeRack(entity.isShoeRack()).tv(entity.isTv()).washer(entity.isWasher()).houseNum(entity.getHouse().getHouseNum()).build();
        return dto;
    }

}
