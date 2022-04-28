package org.suhyun.findhouse.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.suhyun.findhouse.security.handler.MemberLoginSuccessHandler;
import org.suhyun.findhouse.security.service.MemberUserDetailsService;

@Configuration
@EnableWebSecurity
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MemberUserDetailsService memberUserDetailsService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MemberLoginSuccessHandler successHandler(){
        return new MemberLoginSuccessHandler(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/sample/all").permitAll()
                .antMatchers("/sample/member").hasRole("USER")
                .antMatchers("/member/login").permitAll()
                .antMatchers("/house/list").permitAll()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated();

        http
                .formLogin();
                //.loginPage("/member/login")
                //.loginProcessingUrl("/login")
                //.defaultSuccessUrl("/house/list")
                //.usernameParameter("id");

        http.oauth2Login().successHandler(successHandler());
        http.rememberMe().tokenValiditySeconds(60*60*7).userDetailsService(userDetailsService());
        http.logout()
                .logoutSuccessUrl("/house/list");

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/img/**", "/js/**", "/lib/**", "/vendor/**");
    }

}
