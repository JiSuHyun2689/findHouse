package org.suhyun.findhouse.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Member extends BaseEntity{

    @Id
    private String id;

    @Column(nullable = false)
    private String password, userName, nickName;

    @Column(nullable = false)
    private LocalDate birth;

    @Column(nullable = false)
    private int contract;

    @ColumnDefault("false")
    private boolean fromSocial;

}
