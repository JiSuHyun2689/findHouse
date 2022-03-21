package org.suhyun.findhouse.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Member extends BaseEntity {

    @Id
    private String id;

    @Column(nullable = false)
    private String password, userName, nickName;

    @Column(nullable = false)
    private LocalDate birth;

    @Column(nullable = false)
    private String contact;

    @ColumnDefault("false")
    private boolean fromSocial;

    public void changeInfo(String password, String userName, String nickName, LocalDate birth, String contract) {
        this.password = password;
        this.userName = userName;
        this.nickName = nickName;
        this.birth = birth;
        this.contact = contract;
    }

    public void changeModDate(LocalDateTime modDate){
        this.modDate = modDate;
    }

}
