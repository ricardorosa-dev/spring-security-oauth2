package dev.ricardorosa.Validations.security;

import org.springframework.beans.factory.annotation.Autowired;

public enum ApplicationUserPermission {
	USER_READ("user:read"),
	USER_WRITE("user:write"),
	ADMIN_READ("admin:read"),
	ADMIN_WRITE("admin:write");
	
	private final String permission;
	
	@Autowired
	ApplicationUserPermission(String permission) {
		this.permission = permission;
	}
	
	public String getPermission() {
		return permission;
	}

}
