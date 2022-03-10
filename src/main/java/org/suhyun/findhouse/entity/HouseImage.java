package org.suhyun.findhouse.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "house")
public class HouseImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageNum;

    private String imageName, path, uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_num")
    private House house;


    @Override
    public boolean equals(Object o) {
        HouseImage houseImage = (HouseImage) o;
        if (this.getUuid().equals(houseImage.getUuid()))
            return true;
        else
            return false;

    }

}
