package org.suhyun.findhouse.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"house", "member"})
public class Review extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewNum;

    @Column(nullable = false)
    private String targetId;

    @Column(nullable = false)
    private int grade;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_num")
    private House house;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewer_Id")
    private Member member;

    public void changeGrade(int grade){
        this.grade = grade;
    }

    public void changeContent(String content){
        this.content = content;
    }
}
