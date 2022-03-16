package org.suhyun.findhouse.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.suhyun.findhouse.dto.WishDTO;
import org.suhyun.findhouse.entity.Wish;
import org.suhyun.findhouse.service.WishService;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/wish")
@RequiredArgsConstructor
public class WishController {

    private final WishService wishService;

    @GetMapping("/{houseNum}/all")
    public ResponseEntity<Integer> getList(@PathVariable("houseNum") Long houseNum) {

        log.info("wish list ----  houseNum : " + houseNum + " ------------------------------------------------------");

        List<WishDTO> wishDTOList = wishService.getListOfHouse(houseNum);

        return new ResponseEntity<>(wishDTOList.size(), HttpStatus.OK);
    }


    @PostMapping("/{houseNum}")
    public ResponseEntity<Long> addWish(@RequestBody WishDTO wishDTO) {

        log.info("add wish ----  houseNum : " + wishDTO.getHouseNum() + " ------------------------------------------------------");
        log.info("wishDTO : " + wishDTO);

        Long wishNum = wishService.register(wishDTO);

        return new ResponseEntity<>(wishNum, HttpStatus.OK);
    }

    @DeleteMapping("/{houseNum}/{id}")
    public ResponseEntity<Long> removeWish(@PathVariable String id, @PathVariable Long houseNum) {

        log.info("remove wish ----  houseNum : " + houseNum + " ------------------------------------------------------");
        log.info("id : " + id);

        wishService.removeByMember(id, houseNum);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{houseNum}/{id}")
    public ResponseEntity<WishDTO> getHouseWishOfMember(@PathVariable String id, @PathVariable Long houseNum) {

        log.info("get house wish of member / houseNum : " + houseNum + ", id: " + id);

        WishDTO wishDTO = wishService.getHouseWishOfMember(id, houseNum);

        return new ResponseEntity<>(wishDTO, HttpStatus.OK);
    }
}
