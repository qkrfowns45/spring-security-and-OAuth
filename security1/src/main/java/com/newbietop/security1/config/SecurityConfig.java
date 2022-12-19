package com.newbietop.security1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
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
		  .requestMatchers("/manager/**").hasAnyRole("ROLE_ADMIN","ROLE_MANAGER")
		  .requestMatchers("/admin/**").hasRole("ROLE_ADMIN")
		  .anyRequest().permitAll()
		  .and()
		  .formLogin()
		  .loginPage("/loginForm")
		  .loginProcessingUrl("/login") //login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행해 준다!.
		  .defaultSuccessUrl("/");
		  
		
	    return http.build();
	}
}
