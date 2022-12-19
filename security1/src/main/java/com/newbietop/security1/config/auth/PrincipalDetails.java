package com.newbietop.security1.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.newbietop.security1.model.User;

//시큐리티가 /login 주소를 낚아채서 로그인을 진행한다. 로그인을 진행 완료하면 session에 넣어준다!!(Security ContextHolder)
//들어갈 수 있는 오브젝트 형태는 => Authentication객체
//Authentication 안에 User정보가 있어야한다.
//User오브젝트 타입=> UserDetails 타입 객체

// Security Session => Authentication => UserDetails
public class PrincipalDetails implements UserDetails{

	private User user; //콤포지션
	
	public PrincipalDetails(User user) {
		this.user = user;
	}
	
	//해당 User의 권한을 리턴하는 곳
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return user.getRole();
			}
		});
		return null;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	

}
