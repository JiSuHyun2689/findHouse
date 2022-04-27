package org.suhyun.findhouse.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.suhyun.findhouse.entity.Member;
import org.suhyun.findhouse.repository.MemberRepository;
import org.suhyun.findhouse.security.dto.AuthMemberDTO;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("MemberUserDetailsService loadUserByUsername : " + username);

        Optional<Member> result = memberRepository.findById(username, false);

        if (result.isEmpty()) {
            throw new UsernameNotFoundException("Check Email or Social");
        }

        Member member = result.get();

        log.info("-------------------------------------------------------------------------");
        log.info(member);
        log.info("-------------------------------------------------------------------------");

        AuthMemberDTO authMember = new AuthMemberDTO(
                member.getId(),
                member.getPassword(),
                member.isFromSocial(),
                member.getRoleSet().stream().map(role ->
                        new SimpleGrantedAuthority("ROLE_" + role.name()))
                        .collect(Collectors.toList()));

        authMember.setName(member.getName());
        authMember.setFromSocial(member.isFromSocial());

        log.info("-------------------------------------------------------------------------");
        log.info(authMember);
        log.info("-------------------------------------------------------------------------");


        return authMember;
    }
}
