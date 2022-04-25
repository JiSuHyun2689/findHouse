package org.suhyun.findhouse.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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
    private String password, name, nickName;

    @Column(nullable = false)
    private LocalDate birth;

    @Column(nullable = false)
    private String contact;

    @ColumnDefault("false")
    private boolean fromSocial;

    public void changeInfo(String password, String name, String nickName, LocalDate birth, String contract) {
        this.password = password;
        this.name = name;
        this.nickName = nickName;
        this.birth = birth;
        this.contact = contract;
    }

    public void changeModDate(LocalDateTime modDate){
        this.modDate = modDate;
    }

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

    public void addMemberRole(MemberRole memberRole){
        roleSet.add(memberRole);
    }
}
