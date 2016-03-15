package com.shilong.login.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shilong.core.constant.Constant;
import com.shilong.nsfw.user.entity.User;
import com.shilong.nsfw.user.entity.UserRole;
import com.shilong.nsfw.user.service.UserService;

public class LoginAction extends ActionSupport {
	@Resource
	private UserService userService;
	
	private User user;
	private String loginResult;
	
	
	public String toLoginUI() {
		return "loginUI";
	}
	
	public String login() {
		
		if(user!=null){
			if(StringUtils.isNotBlank(user.getAccount())&&StringUtils.isNotBlank(user.getPassword())){
				List<User> list=userService.findUserByAccountAndPassword(user.getAccount(),user.getPassword());
				if(!list.isEmpty()){
					User u=list.get(0);
					List<UserRole> lur=userService.getUserRolesByUserId(u.getId());
					
					u.setUserRoles(lur);
					ActionContext.getContext().getSession().put(Constant.USER,u);
					
					//将用户登录信息记录到日志文件中
					
					Log log=LogFactory.getLog(getClass());
					
					log.info("用户账号为："+u.getAccount()+",用户名为:"+u.getName()+"的用户登陆了！");
					
					return "home";
				}else{
					loginResult="账号或密码错误！";
				}
			}else{
				loginResult="账号或密码不能为空！";
			}
		}else{
			loginResult="请输入账号密码";
		}
		
		
		return toLoginUI();
	}
	
	
	public String logout() {
		ActionContext.getContext().getSession().remove(Constant.USER);
		return toLoginUI();
	}
	
	public String toNoPermissionUI() {
		return "noPermissionUI";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getLoginResult() {
		return loginResult;
	}

	public void setLoginResult(String loginResult) {
		this.loginResult = loginResult;
	}
	
	
}
