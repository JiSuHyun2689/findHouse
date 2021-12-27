package org.suhyun.findhouse.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "house")
public class HouseImage{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageNum;

    private String imageName, path, uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_num")
    private House house;

}
