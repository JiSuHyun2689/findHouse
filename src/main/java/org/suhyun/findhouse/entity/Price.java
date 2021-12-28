package org.suhyun.findhouse.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "house")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long price_num;

    @ColumnDefault("0")
    private int price, deposit, monthly;

    public void changePrices(int price, int deposit, int monthly){
        this.price = price;
        this.deposit = deposit;
        this.monthly = monthly;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_num")
    private House house;
}
