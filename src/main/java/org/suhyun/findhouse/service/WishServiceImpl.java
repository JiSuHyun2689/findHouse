package org.suhyun.findhouse.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.suhyun.findhouse.dto.WishDTO;
import org.suhyun.findhouse.entity.House;
import org.suhyun.findhouse.entity.Wish;
import org.suhyun.findhouse.repository.WishRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Log4j2
@RequiredArgsConstructor
public class WishServiceImpl implements WishService {


    @Autowired
    private WishRepository repository;

    @Override
    public List<WishDTO> getListOfHouse(Long houseNum) {

        House house = House.builder().houseNum(houseNum).build();

        List<Wish> result = repository.findByHouse(house);

        return result.stream().map(wish -> entityToDto(wish)).collect(Collectors.toList());
    }

    @Override
    public List<WishDTO> getListOfMember(String id) {
        return null;
    }

    @Override
    public WishDTO getHouseWishOfMember(String id, Long houseNum) {

        Wish wish = repository.findByIdAndHouseNum(id, houseNum);

        return wish == null ? null : entityToDto(wish);
    }

    @Override
    public Long register(WishDTO wishDTO) {

        Wish wish = dtoToEntity(wishDTO);

        repository.save(wish);

        return wish.getWishNum();
    }

    @Override
    public void remove(Long wishNum) {

        repository.deleteById(wishNum);
    }

    @Override
    public void removeByHouse(Long houseNum) {

        repository.deleteByHouse(houseNum);
    }

    @Override
    public void removeByMember(String id, Long houseNum) {

        repository.deleteByMember(id, houseNum);
    }
}
