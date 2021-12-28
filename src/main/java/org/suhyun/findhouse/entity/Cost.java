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
    private Long cost_num;

    private int totalCost;

    private boolean electricity, gas, water, tv, internet, parking, etc;

    private String content;

    public void changeContent(String content){
        this.content = content;
    }

    public void changeCosts(int totalCost, boolean electricity, boolean gas, boolean water, boolean tv, boolean internet, boolean parking, boolean etc){
        this.totalCost = totalCost;
        this.electricity = electricity;
        this.gas = gas;
        this.water = water;
        this.tv = tv;
        this.internet = internet;
        this.parking = parking;
        this.etc = etc;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_num")
    private House house;
}
