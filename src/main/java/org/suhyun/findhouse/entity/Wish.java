package org.suhyun.findhouse.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"house", "member"})
public class Wish extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishNum;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_num")
    private House house;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Member member;
}
