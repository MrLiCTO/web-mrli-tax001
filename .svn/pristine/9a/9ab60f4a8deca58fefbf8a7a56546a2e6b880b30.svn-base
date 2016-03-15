package com.shilong.nsfw.user.service;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletOutputStream;

import com.shilong.core.exception.ServiceException;
import com.shilong.core.service.BaseService;
import com.shilong.nsfw.user.entity.User;
import com.shilong.nsfw.user.entity.UserRole;

public interface UserService extends BaseService<User>{
		
		//导出用户列表
		public void exportExcel(List<User> userList, ServletOutputStream out);
		//导入
		public void importExcel(File userExcel, String userExcelFileName);
		//根据账号ID校验
		public List<User> getUserByAccountAndId(String account,String id);
		
		
		public void updateUserAndRole(User user, String... roleIds);
		
		
		public void saveUserAndRole(User user, String... roleIds);
		public List<UserRole> getUserRolesByUserId(String id);
		public List<User> findUserByAccountAndPassword(String account, String password);   
}
