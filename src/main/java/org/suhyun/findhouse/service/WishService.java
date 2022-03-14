package org.suhyun.findhouse.service;

import org.suhyun.findhouse.dto.WishDTO;
import org.suhyun.findhouse.entity.House;
import org.suhyun.findhouse.entity.Member;
import org.suhyun.findhouse.entity.Wish;

import java.util.List;

public interface WishService {

    List<WishDTO> getListOfHouse(Long houseNum);

    List<WishDTO> getListOfMember(String id);

    Long register(WishDTO wishDTO);

    void remove(Long wishNum);

    void removeByHouse(Long houseNum);

    void removeByMember(String id);

    default Wish dtoToEntity(WishDTO dto){

        Wish entity = Wish.builder().wishNum(dto.getWishNum())
                .house(House.builder().houseNum(dto.getHouseNum()).build())
                .member(Member.builder().id(dto.getId()).build()).build();
        return entity;
    }

    default WishDTO entityToDto(Wish entity){

        WishDTO dto = WishDTO.builder().wishNum(entity.getWishNum())
                .id(entity.getMember().getId())
                .houseNum(entity.getHouse().getHouseNum())
                .modDate(entity.getModDate())
                .regDate(entity.getRegDate())
                .build();
        return dto;
    }
}
