package org.suhyun.findhouse.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class House extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long houseNum;

    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String title, status, address, content, contractType, buildingType;

    @Column(nullable = false)
    private double area;

    @Column(nullable = false)
    private int minTerm, theFloor, wholeFloor, brokerage;

    @ColumnDefault("0")
    private int view;

    private LocalDate moveInDate, completionDate;

    @Column(nullable = false)
    private boolean pet, elevator, parking, loan;

    public void changeTitle(String title) { this.title = title; }
    public void changeStatus(String status){
        this.status = status;
    }

    public void changeContent(String content){
        this.content = content;
    }

    public void changeInfo(String address, String contractType, int minTerm, int brokerage, LocalDate moveInDate, boolean loan){
        this.address = address;
        this.contractType = contractType;
        this.minTerm = minTerm;
        this.brokerage = brokerage;
        this.moveInDate = moveInDate;
        this.loan = loan;
    }

    @OneToOne(mappedBy = "house")
    private Option option;

    @OneToOne(mappedBy = "house")
    private Price price;

    @OneToOne(mappedBy = "house")
    private Structure structure;

    @OneToOne(mappedBy = "house")
    private Cost cost;

}
