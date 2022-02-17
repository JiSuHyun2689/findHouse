package org.suhyun.findhouse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.suhyun.findhouse.entity.House;
import org.suhyun.findhouse.entity.Member;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDTO {

    private Long reviewNum;

    private String targetId, raterId, content;

    private int grade;

    private Long houseNum;

    private LocalDateTime regDate, modDate;

}
