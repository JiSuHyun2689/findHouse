package org.suhyun.findhouse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HouseDTO {

    private Long houseNum;

    private String id;

    private String status, address, content;

    private double area;

    private int contractType, buildingType, minTerm, theFloor, wholeFloor, brokerage, view;

    private LocalDate moveInDate, completionDate;

    private boolean pet, elevator, parking, loan;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

}
