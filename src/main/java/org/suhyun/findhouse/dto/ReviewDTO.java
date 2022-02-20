package org.suhyun.findhouse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDTO {

    private Long reviewNum;

    private String targetId, reviewerId, nickName, content;

    private double grade;

    private Long houseNum;

    private LocalDateTime regDate, modDate;

}
