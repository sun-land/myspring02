package com.sparta.spring02.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // 암호화 알고리즘을 Bean으로 등록
    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {

        // h2-console 사용하려면면 CSRF뿐만아니라 FrameOptions도 무시해줘야한다.
        // h2-console 사용에 대한 허용 (CSRF, FrameOptions 무시)
        web
                .ignoring()
                .antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // csrf 보호가 자동으로 활성화되어있다.
        // csrf 방어는 GET은 방어 대상에 두지 않고 POST, PATCH, DELETE같이 데이터 변조가 가능한 메소드에 적용된다.
        // 그래서 로그인 안한 상태로 POST요청 할 때 csrf 무시하도록 한 번 더 설정해줘야함

//        // 회원 관리 처리 API (POST /user/**) 에 대해 CSRF 무시
//        http.csrf()
//                .ignoringAntMatchers("/user/**");

        // 위 방법으로 하다가는 허락해주고 싶은 POST요청 있을 때마다 계속 추가해야한다.
        // CSRF protection 을 비활성화
        http.csrf().disable();


        http.authorizeRequests()
                // image 폴더를 login 없이 허용
                .antMatchers("/images/**").permitAll()
                // css 폴더를 login 없이 허용
                .antMatchers("/css/**").permitAll()
                // 회원 관리 처리 API 전부를 login 없이 허용
                .antMatchers("/user/**").permitAll()
                // 인덱스 페이지 가능
                .antMatchers("/").permitAll()
                // 전체조회 API 가능 (근데 이거 POST면 게시글 저장도 되는데 어떡하지?)
                .antMatchers("/api/posts").permitAll()
                // 상세페이지 이동 가능
                .antMatchers("/posts/**/detail").permitAll()
                // 상세페이지 게시글 조회 API 가능(근데 이거 POST면 게시글 삭제도 되는데 어떻게 해야하지)
                .antMatchers("/api/posts/**").permitAll()
                // 상세페이지 게시글 조회 API 가능(근데 이거 POST면 댓글 저장도 되는데 어떡하지)
                .antMatchers("/api/posts/**/comments").permitAll()
                // 어떤 요청이든 '인증' 해라
                .anyRequest().authenticated()
                .and()
                    // [로그인 기능] 로그인 기능 허용
                    .formLogin()
                    // 로그인 View 제공 (로그인페이지) (GET요청 /user/login)
                    .loginPage("/user/login")
                    // 로그인 처리 (POST요청 /user/login)
                    .loginProcessingUrl("/user/login")
                    // 로그인 처리 후 성공 시 URL
                    .defaultSuccessUrl("/")
                    // 로그인 처리 후 실패 시 URL
                    .failureUrl("/user/login?error")
                    .permitAll()
                .and()
                    // [로그아웃 기능] 로그아웃 기능 허용
                    .logout()
                    // 로그아웃 처리 URL (원래 GET으로 설정을 했었는데 POST로 해야한다 뭔소리래? 2주차 9강)
                    // (CSRF protection 을 disable 하면 GET /user/logout 으로도 사용 가능이라던데??)
                    .logoutUrl("/user/logout")
                    // 로그아웃 성공 후 화면
                    .logoutSuccessUrl("/")
                    .permitAll();
    }
}
