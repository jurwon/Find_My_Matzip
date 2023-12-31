package com.matzip.config;

import com.matzip.config.oauth.PrincipalOauth2UserService;
import com.matzip.service.UsersService;
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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UsersService usersService;
    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/users/login") //사용자 정의 로그인 페이지
                .defaultSuccessUrl("/") // 로그인 성공 후 이동 페이지
                .usernameParameter("userid")// 아이디 파라미터명 설정
                .passwordParameter("user_pwd")
                .failureUrl("/users/login/error") // 로그인 실패 후 이동 페이지

                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/users/logout")) //사용자 정의 로그아웃 페이지
                .logoutSuccessUrl("/") // 로그아웃 후 이동 페이지



//                .and()
//                .oauth2Login()
//                .loginPage("/users/login") // OAuth2 로그인 페이지
//                .userInfoEndpoint()
//                .userService(principalOauth2UserService);
//
        ;

        http.authorizeRequests()
                .mvcMatchers("/", "/users/**","/item/**", "/images/**", "/map","/restaurant/main").permitAll()
                .mvcMatchers("/users/admin/**","/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
        ;

       /* http.authorizeRequests()
                .mvcMatchers("/","/admin/**", "/users/**","/item/**", "/images/**", "/map","/restaurant/main").permitAll()
                .mvcMatchers("/users/userspage/","/admin/boards").hasRole("ADMIN")
                .anyRequest().authenticated()
        ;*/

        http.exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
        ;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usersService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
    }


}