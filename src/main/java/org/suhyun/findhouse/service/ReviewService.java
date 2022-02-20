package org.suhyun.findhouse.service;

import org.suhyun.findhouse.dto.ReviewDTO;
import org.suhyun.findhouse.entity.House;
import org.suhyun.findhouse.entity.Member;
import org.suhyun.findhouse.entity.Review;

import java.util.List;

public interface ReviewService {

    List<ReviewDTO> getListOfHouse(Long houseNum);

    Long register(ReviewDTO houseReviewDTO);

    void modify(ReviewDTO houseReviewDTO);

    void remove(Long reviewNum);

    default Review dtoToEntity(ReviewDTO dto){

        Review entity = Review.builder()
                .reviewNum(dto.getReviewNum())
                .house(House.builder().houseNum(dto.getHouseNum()).build())
                .targetId(dto.getTargetId())
                .member(Member.builder().id(dto.getReviewerId()).build())
                .grade(dto.getGrade())
                .content(dto.getContent()).build();

        return entity;
    }

    default ReviewDTO entityToDto(Review entity){

        ReviewDTO dto = ReviewDTO.builder()
                .reviewNum(entity.getReviewNum())
                .houseNum(entity.getHouse().getHouseNum())
                .targetId(entity.getTargetId())
                .nickName(entity.getMember().getNickName())
                .reviewerId(entity.getMember().getId())
                .grade(entity.getGrade())
                .modDate(entity.getModDate())
                .regDate(entity.getRegDate())
                .content(entity.getContent()).build();

        return dto;
    }
}