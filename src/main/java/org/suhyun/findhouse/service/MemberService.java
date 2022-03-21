package org.suhyun.findhouse.service;

import org.suhyun.findhouse.dto.MemberDTO;
import org.suhyun.findhouse.entity.Member;

public interface MemberService {

    String register(MemberDTO dto);

    MemberDTO read(String id);

    String modify(MemberDTO dto);

    void remove(String id);

    default MemberDTO entityToDTo(Member entity){

        MemberDTO dto = MemberDTO.builder().id(entity.getId())
                .birth(entity.getBirth())
                .contact(entity.getContact())
                .fromSocial(entity.isFromSocial())
                .nickName(entity.getNickName())
                .password(entity.getPassword())
                .userName(entity.getUserName())
                .build();

        return dto;
    }

    default Member dtoToEntity(MemberDTO dto){

        Member entity = Member.builder().nickName(dto.getNickName())
                .birth(dto.getBirth())
                .contact(dto.getContact())
                .fromSocial(dto.isFromSocial())
                .id(dto.getId())
                .password(dto.getPassword())
                .userName(dto.getUserName())
                .build();

        return entity;
    }
}
