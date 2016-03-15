package com.shilong.nsfw.role.entity;

import java.io.Serializable;

public class RolePrivilege implements Serializable {
	private RolePrivilegeId id;
	
	

	public RolePrivilege(RolePrivilegeId id) {
		this.id = id;
	}

	public RolePrivilege() {	
	}

	public RolePrivilegeId getId() {
		return id;
	}

	public void setId(RolePrivilegeId id) {
		this.id = id;
	}
	
	
}
