package org.suhyun.findhouse.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.suhyun.findhouse.dto.ReviewDTO;
import org.suhyun.findhouse.service.ReviewService;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{houseNum}/all")
    public ResponseEntity<List<ReviewDTO>> getList(@PathVariable("houseNum")Long houseNum){

        log.info("review list -----------------------------------------------");
        log.info("houseNum : " + houseNum);

        List<ReviewDTO> reviewDTOList = reviewService.getListOfHouse(houseNum);

        return new ResponseEntity<>(reviewDTOList, HttpStatus.OK);
    }


    @PostMapping("/{houseNum}")
    public ResponseEntity<Long> addReview(@RequestBody ReviewDTO houseReviewDTO){

        log.info("add review -------------------------------------------------");
        log.info("reviewDTO : " + houseReviewDTO);

        Long reviewNum = reviewService.register(houseReviewDTO);

        return new ResponseEntity<>(reviewNum, HttpStatus.OK);
    }


    @PutMapping("/{houseNum}/{reviewNum}")
    public ResponseEntity<Long> houseReview(@PathVariable Long reviewNum, @RequestBody ReviewDTO houseReviewDTO){

        log.info("modify review -----------------------------------------------");
        log.info("reviewDTO : " + houseReviewDTO);

        reviewService.modify(houseReviewDTO);

        return new ResponseEntity<>(reviewNum, HttpStatus.OK);
    }


    @DeleteMapping("/{houseNum}/{reviewNum}")
    public ResponseEntity<Long> removeReview(@PathVariable Long reviewNum){

        log.info("remove review -----------------------------------------------");
        log.info("reviewNum : " + reviewNum);

        reviewService.remove(reviewNum);

        return new ResponseEntity<>(reviewNum, HttpStatus.OK);
    }
}
