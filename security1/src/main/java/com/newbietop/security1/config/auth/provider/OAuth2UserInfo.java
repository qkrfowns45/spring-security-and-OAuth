package com.newbietop.security1.config.auth.provider;

public interface OAuth2UserInfo {
	String getProciderId();
	String getProvider();
	String getEmail();
	String getName();
}
