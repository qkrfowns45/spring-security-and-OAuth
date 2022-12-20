package com.newbietop.security1.config;

import org.springframework.beans.factory.annotation.Autowired;

//구글 로그인이 완료된 후의 후처리 1.코드받기(인증) 2.엑세스토큰(권한) 3.사용자프로필 정보를 가져옴 
//4-1 그 정보를 토대로 회원가입을 자동으로 진행
//4-2 추가적인 구성이 필요하면 추가 회원 가입창 등장

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.newbietop.security1.config.oauth.PrincipalOauth2UserService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true) //secured 어노테이션 활성, preAuthorize 어노테이션 활성화
public class SecurityConfig {
	
	@Autowired
	private PrincipalOauth2UserService principalOauth2UserService;
	
	//해당 메서드의 리턴되는 오브젝트를 ioc로 등록된다.
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeHttpRequests()
			  .requestMatchers("/user/**").authenticated()
		      .requestMatchers("/admin/**").hasRole("ADMIN")
		      .requestMatchers("/manager/**").hasAnyRole("ADMIN","MANAGER")
			  .anyRequest().permitAll()
			.and()
			  .formLogin()
			  .loginPage("/loginForm")
			  .loginProcessingUrl("/login") //login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행해 준다!.
			  .defaultSuccessUrl("/")
			.and()
			  .oauth2Login()
			  .loginPage("/loginForm")
			  .userInfoEndpoint()
			  .userService(principalOauth2UserService);// tip.코드x(엑세스토큰+사용자프로필정보) oauth의 장점
		  
		
	    return http.build();
	}
}
