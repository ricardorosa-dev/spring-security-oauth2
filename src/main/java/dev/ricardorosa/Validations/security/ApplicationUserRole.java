package dev.ricardorosa.Validations.security;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


import com.google.common.collect.Sets;
import static dev.ricardorosa.Validations.security.ApplicationUserPermission.*; 

public enum ApplicationUserRole {
	USER(Sets.newHashSet(USER_READ, USER_WRITE)),
	ADMIN(Sets.newHashSet(USER_READ, USER_WRITE, ADMIN_READ, ADMIN_WRITE));
	
	private final Set<ApplicationUserPermission> permissions;

	ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
		this.permissions = permissions;
	}
	
	public Set<ApplicationUserPermission> getPermissions() {
		return permissions;
	}
}
