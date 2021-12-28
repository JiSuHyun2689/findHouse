package org.suhyun.findhouse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OptionDTO {

    private Long option_num;

    private boolean tv, airConditioner, refrigerator, washer, dryer, induction, gasStove, sink, desk, bookshelf, bed, closet, dishwasher, shoeRack;

}
