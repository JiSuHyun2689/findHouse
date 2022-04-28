package org.suhyun.findhouse.security.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.suhyun.findhouse.security.dto.AuthMemberDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class MemberLoginSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private PasswordEncoder passwordEncoder;

    public MemberLoginSuccessHandler(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        log.info("--------------------------------------------------------");
        log.info("onAuthenticationsSuccess ");

        AuthMemberDTO authMember = (AuthMemberDTO) authentication.getPrincipal();

        boolean fromSocial = authMember.isFromSocial();

        log.info("회원 정보를 수정해야 하나요? " + fromSocial);

        boolean passwordResult = passwordEncoder.matches("1111", authMember.getPassword());

        if(fromSocial && passwordResult){
            redirectStrategy.sendRedirect(request, response, "/member/modify?from=social&id="+authMember.getId());
        }

    }
}
