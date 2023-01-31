package com.swcoaching.example1.config.auth;

import com.swcoaching.example1.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;


    //SecurityFilterChain 람다식 작성방식
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/","/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                        .requestMatchers("/api/v1/**").permitAll()//hasRole(Role.USER.name()) // 개발 중 설정
                        .anyRequest().authenticated())
                .logout(logout -> logout
                        .logoutSuccessUrl("/"))
                .oauth2Login(oauth2Login -> oauth2Login
                        .userInfoEndpoint()
                        .userService(customOAuth2UserService));

        return http.build();
    }
}
