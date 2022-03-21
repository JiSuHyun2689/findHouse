package org.suhyun.findhouse.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.suhyun.findhouse.dto.MemberDTO;
import org.suhyun.findhouse.entity.Member;
import org.suhyun.findhouse.repository.MemberRepository;

import java.time.LocalDateTime;


@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository repository;

    @Override
    public String register(MemberDTO dto) {

        log.info("Member Register -----------------------------------------------------");
        log.info(dto);

        String id = repository.save(dtoToEntity(dto)).getId();

        return id;
    }

    @Override
    public MemberDTO read(String id) {

        log.info("Member read -----------------------------------------------------");
        log.info(id);

        MemberDTO dto = entityToDTo(repository.getById(id));

        return dto;
    }

    @Override
    public String modify(MemberDTO dto) {

        log.info("Member Modify -----------------------------------------------------");
        log.info(dto);

        Member member = repository.getById(dto.getId());

        if (member != null) {
            member.changeInfo(dto.getPassword(), dto.getUserName(), dto.getNickName(), dto.getBirth(), dto.getContact());
            member.changeModDate(LocalDateTime.now());
        }

        repository.save(member);
        return null;
    }

    @Override
    public void remove(String id) {

        log.info("Member Remove -----------------------------------------------------");
        log.info(id);

        repository.deleteById(id);
    }
}
