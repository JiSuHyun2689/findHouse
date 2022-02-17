package org.suhyun.findhouse.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"option", "price", "structure", "cost"})
public class House extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long houseNum;

    @Column(nullable = false)
    private String id;

    private String status;

    @Column(nullable = false)
    private String title, address, content, contractType, buildingType;

    @Column(nullable = false)
    private double area;

    @Column(nullable = false)
    private int minTerm, theFloor, wholeFloor, brokerage;

    @ColumnDefault("0")
    private int view;

    @Column(nullable = false)
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

    public void changeInfo(String address, String contractType, int minTerm, int brokerage, LocalDate moveInDate, LocalDate completionDate, boolean loan, boolean elevator, boolean pet, boolean parking){
        this.address = address;
        this.contractType = contractType;
        this.minTerm = minTerm;
        this.brokerage = brokerage;
        this.moveInDate = moveInDate;
        this.completionDate = completionDate;
        this.loan = loan;
        this.elevator = elevator;
        this.pet = pet;
        this.parking = parking;
    }



    @OneToOne(mappedBy = "house", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Option option;

    @OneToOne(mappedBy = "house", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Price price;

    @OneToOne(mappedBy = "house", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Structure structure;

    @OneToOne(mappedBy = "house", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Cost cost;

}
