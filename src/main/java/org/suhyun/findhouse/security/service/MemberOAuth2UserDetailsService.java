package org.suhyun.findhouse.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.suhyun.findhouse.entity.Member;
import org.suhyun.findhouse.entity.MemberRole;
import org.suhyun.findhouse.repository.MemberRepository;
import org.suhyun.findhouse.security.dto.AuthMemberDTO;

import java.sql.SQLOutput;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberOAuth2UserDetailsService extends DefaultOAuth2UserService {

    private final MemberRepository repository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("---------------------------------------------------------");
        log.info("userRequest : " + userRequest);

        String clientName = userRequest.getClientRegistration().getClientName();

        log.info("clientName : " + clientName);
        log.info(userRequest.getAdditionalParameters());

        OAuth2User oAuth2User = super.loadUser(userRequest);

        log.info("==========================================================");
        oAuth2User.getAttributes().forEach((k, v) -> {
            log.info(k + " : " + v);
        });
        log.info("==========================================================");

        String email = null;
        Map<String, Object> attributes = null;

        if (clientName.equals("Google")) {
            email = oAuth2User.getAttribute("email");
            attributes = oAuth2User.getAttributes();
        } else if (clientName.equals("Naver")) {
            attributes = ofNaver(oAuth2User.getAttributes());
            email = (String) attributes.get("email");
        } else if (clientName.equals("Kakao")) {
            attributes = ofKakao(oAuth2User.getAttributes());
            email = (String) attributes.get("email");
        }


        log.info("EMAIL : " + email);

        Member member = saveSocialMember(email);

        AuthMemberDTO authMember = new AuthMemberDTO(
                member.getId(),
                member.getPassword(),
                true,
                member.getRoleSet().stream().map(
                        role -> new SimpleGrantedAuthority("ROLE_" + role.name())
                ).collect(Collectors.toList()),
                attributes
        );
        authMember.setName(member.getName());
        return authMember;
    }

    private Member saveSocialMember(String email) {

        Optional<Member> result = repository.findById(email, true);

        if (result.isPresent()) {
            return result.get();
        }

        Member member = Member.builder()
                .id(email)
                .name(email)
                .password(passwordEncoder.encode("1111"))
                .fromSocial(true)
                .build();

        member.addMemberRole(MemberRole.USER);

        repository.save(member);

        return member;
    }


    private Map<String, Object> ofNaver(Map<String, Object> attributes) {
        return (Map<String, Object>) attributes.get("response");
    }

    private Map<String, Object> ofKakao(Map<String, Object> attributes) {
        return (Map<String, Object>) attributes.get("kakao_account");
    }
}
