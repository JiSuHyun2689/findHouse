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
public class WishDTO {

    private Long wishNum;

    private Long houseNum;

    private String id;

    private LocalDateTime regDate, modDate;
}
