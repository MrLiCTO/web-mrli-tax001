package com.shilong.core.permission.impl;

import java.util.List;

import javax.annotation.Resource;

import com.shilong.core.permission.PermissionCheck;
import com.shilong.nsfw.role.entity.Role;
import com.shilong.nsfw.role.entity.RolePrivilege;
import com.shilong.nsfw.user.entity.User;
import com.shilong.nsfw.user.entity.UserRole;
import com.shilong.nsfw.user.service.UserService;

public class PermissionCheckImpl implements PermissionCheck {
	
	@Resource
	private UserService userService;
	
	@Override
	public boolean accessible(User user, String string) {
		List<UserRole> userRoles=user.getUserRoles();
		if(userRoles==null){
			userRoles=userService.getUserRolesByUserId(user.getId());
		}

		for(UserRole ur:userRoles){
			Role role =ur.getId().getRole();
			
				for(RolePrivilege rp:role.getRolePrivileges()){
					if(string.equals(rp.getId().getCode())){
						return true;
					}
				}
			
		}
		
		return false;
	}

}
