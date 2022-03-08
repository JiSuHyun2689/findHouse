package org.suhyun.findhouse.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "house")
public class Cost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long costNum;

    private int totalCost;

    private boolean electricity, gas, water, tv, internet, parking, etc;

    private String content;

    public void changeCosts(int totalCost, boolean electricity, boolean gas, boolean water, boolean tv, boolean internet, boolean parking, boolean etc, String content){
        this.totalCost = totalCost;
        this.electricity = electricity;
        this.gas = gas;
        this.water = water;
        this.tv = tv;
        this.internet = internet;
        this.parking = parking;
        this.etc = etc;
        this.content = content;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_num")
    private House house;

}
