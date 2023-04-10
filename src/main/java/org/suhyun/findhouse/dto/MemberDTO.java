package org.suhyun.findhouse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO {

    @Id
    private String id;

    private String password, name, nickName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;

    private String contact;

    private boolean fromSocial;
}
