package org.suhyun.findhouse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.suhyun.findhouse.entity.House;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OptionDTO {

    private Long optionNum;

    private boolean tv, airConditioner, refrigerator, washer, dryer, induction, gasStove, sink, desk, bookshelf, bed, closet, dishwasher, shoeRack;

    private Long houseNum;
}
