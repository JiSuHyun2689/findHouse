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
public class Structure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long structure_num;

    @ColumnDefault("0")
    @Column(nullable = false)
    private int room, toilet, livingRoom, veranda;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_num")
    private House house;

    public void chageStructures(int room , int toilet, int livingRoom, int veranda){
        this.room = room;
        this.toilet = toilet;
        this.livingRoom = livingRoom;
        this.veranda = veranda;
    }
}
