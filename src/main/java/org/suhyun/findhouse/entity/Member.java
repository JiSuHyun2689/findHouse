package org.suhyun.findhouse.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Member extends BaseEntity{

    @Id
    private String id;

    private String password, username;

    private LocalDateTime birth;

    private int contract;

    private boolean fromSocial;

}
