package org.suhyun.findhouse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HouseDTO {

    private Long houseNum;

    private String id;

    private String title, status, address, content, contractType, buildingType;

    private double area;

    private int minTerm, theFloor, wholeFloor, brokerage, view;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate moveInDate, completionDate;

    private boolean pet, elevator, parking, loan;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    @Builder.Default
    private List<HouseImageDTO>imageDTOList = new ArrayList<>();

}
