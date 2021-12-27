package org.suhyun.findhouse.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "house")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long option_num;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_num")
    private House house;

    private boolean tv, airConditioner, refrigerator, washer, dryer, induction, gasStove, sink, desk, bookshelf, bed, closet, dishwasher, shoeRack;

    public void changeOptions(boolean tv, boolean airConditioner, boolean refrigerator, boolean washer, boolean dryer, boolean induction, boolean gasStove,
                              boolean sink, boolean desk, boolean bookshelf, boolean bed, boolean closet, boolean dishwasher, boolean shoeRack){
        this.tv = tv;
        this.airConditioner = airConditioner;
        this.refrigerator = refrigerator;
        this.washer = washer;
        this.dryer = dryer;
        this.induction = induction;
        this.gasStove = gasStove;
        this.sink = sink;
        this.desk = desk;
        this.bookshelf = bookshelf;
        this.bed = bed;
        this.closet = closet;
        this.dishwasher = dishwasher;
        this.shoeRack = shoeRack;
    }
}
