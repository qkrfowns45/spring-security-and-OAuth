package com.newbietop.security1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true) //secured 어노테이션 활성, preAuthorize 어노테이션 활성화
public class SecurityConfig {
	
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
			  .defaultSuccessUrl("/");
		  
		
	    return http.build();
	}
}
