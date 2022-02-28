package org.suhyun.findhouse.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.suhyun.findhouse.dto.ReviewDTO;
import org.suhyun.findhouse.entity.House;
import org.suhyun.findhouse.entity.Review;
import org.suhyun.findhouse.repository.ReviewRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private final ReviewRepository repository;

    @Override
    public List<ReviewDTO> getListOfHouse(Long houseNum) {

        House house = House.builder().houseNum(houseNum).build();

        List<Review> result = repository.findByHouse(house);

        return result.stream().map(houseReview -> entityToDto(houseReview)).collect(Collectors.toList());
    }

    @Override
    public Long register(ReviewDTO houseReviewDTO) {

        Review review = dtoToEntity(houseReviewDTO);

        repository.save(review);

        return review.getReviewNum();
    }

    @Override
    public void modify(ReviewDTO houseReviewDTO) {

        Optional<Review> result = repository.findById(houseReviewDTO.getReviewNum());

        if(result.isPresent()){
            Review review = result.get();
            review.changeGrade(houseReviewDTO.getGrade());
            review.changeContent(houseReviewDTO.getContent());

            repository.save(review);
        }
    }

    @Override
    public void remove(Long reviewNum) {
        repository.deleteById(reviewNum);
    }

    public void removeByHouse(Long houseNum){
        repository.deleteByHouse(houseNum);
    }
}
