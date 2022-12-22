package com.newbietop.security1.config.auth.provider;

import java.util.Map;

public class FacebookUserInfo implements OAuth2UserInfo{

	private Map<String, Object> attributes; //getAttributes()
	
	public FacebookUserInfo(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	
	@Override
	public String getProciderId() {
		return (String)attributes.get("id");
	}

	@Override
	public String getProvider() {
		return "facebook";
	}

	@Override
	public String getEmail() {
		return (String)attributes.get("email");
	}

	@Override
	public String getName() {
		return (String)attributes.get("name");
	}
	

}
