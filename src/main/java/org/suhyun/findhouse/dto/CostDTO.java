package org.suhyun.findhouse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CostDTO {

    private Long costNum;

    private int totalCost;

    private boolean electricity, gas, water, tv, internet, parking, etc;

    private String content;

    private Long houseNum;
}
