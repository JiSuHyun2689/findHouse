package org.suhyun.findhouse.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sample {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sno;

    @Column(length = 200, nullable = false)
    private String text;
}
