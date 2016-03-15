package com.shilong.nsfw.user.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.shilong.core.action.BaseAction;
import com.shilong.core.util.QueryHelper;
import com.shilong.nsfw.role.service.RoleService;
import com.shilong.nsfw.user.entity.User;
import com.shilong.nsfw.user.entity.UserRole;
import com.shilong.nsfw.user.service.UserService;

public class UserAction extends BaseAction {
	private static final long serialVersionUID = -8711398801399055491L;
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	
	
	private User user;
	
	private String strName;
	
	private String[] userRoleIds;
	
	private File headImg;
	private String headImgConyenType;
	private String headImgFileName;
	
	private File userExcel;
	private String userExcelConyenType;
	private String userExcelFileName;
	//userExcel
	//列表页面
	public String listUI() throws Exception{ 
		//userList=userService.findObjects();
		QueryHelper qh=new QueryHelper(User.class,"u");
		
		if (user != null) {
			
			user.setName(URLDecoder.decode(user.getName(),"utf-8"));

			qh.addCondition(" u.name like ?", "%" + user.getName() + "%");
			
		
			
		}

		//qh.addOrderByProperty(" u.createTime ", QueryHelper.ORDER_BY_DESC);
		
		pageResult=userService.getPageResult(qh,getPageNo(),getPageSize());
		
		
		return "listUI";
	}
	//跳转到新增页面
	public String addUI(){
		ActionContext.getContext().getContextMap().put("roleList", roleService.findObjects());
		
		strName=user.getName();
		
		user=new User();
		return "addUI";
	}
	//保存新增
	public String add(){
		try {
			if(user!=null){
				//处理头像
				if(headImg!=null){
					//保存到upload/user
					String filePath=ServletActionContext.getServletContext().getRealPath("upload/user");
					String fileName=UUID.randomUUID().toString()+headImgFileName.substring(headImgFileName.lastIndexOf("."));
					//复制文件
					FileUtils.copyFile(headImg, new File(filePath,fileName)); 
					//设置用户头像路径
					user.setHeadImg("user/"+fileName);
				}
				//userService.save(user);
				userService.saveUserAndRole(user,userRoleIds);
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
		return "list";
	}
	//跳转到编辑页面
	public String editUI(){
		ActionContext.getContext().getContextMap().put("roleList", roleService.findObjects());
		if(user!=null&&user.getId()!=null){
			strName=user.getName();
			user=userService.findObjectById(user.getId());
			List<UserRole> list=userService.getUserRolesByUserId(user.getId());
			if(!list.isEmpty()){
				userRoleIds=new String[list.size()];
				for(int i=0;i<list.size();i++){
					userRoleIds[i]=list.get(i).getId().getRole().getRoleId();
				}
			}
		}
		return "editUI";
	}
	//保存编辑
	public String edit(){
		try {
			if(user!=null){
				//处理头像
				if(headImg!=null){
					//保存到upload/user
					String filePath=ServletActionContext.getServletContext().getRealPath("upload/user");
					String fileName=UUID.randomUUID().toString()+headImgFileName.substring(headImgFileName.lastIndexOf("."));
					//复制文件
					FileUtils.copyFile(headImg, new File(filePath,fileName)); 
					//设置用户头像路径
					user.setHeadImg("user/"+fileName);
				}
				//userService.update(user);
				userService.updateUserAndRole(user,userRoleIds);
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return "list";
	}
	//删除
	public String delete(){
		strName=user.getName();
		if(user!=null&&user.getId()!=null){
			userService.delete(user.getId());
		}
		return "list";
	}

	//批量删除
	public String deleteSelected(){
		strName=user.getName();
		if(selectedRow!=null){
			for(String id:selectedRow){
				userService.delete(id);
			}
		}
		return "list";
	}
	
	
	
	//导出用户列表
	public void exportExcel(){
		try {
			
			//导出
			HttpServletResponse resp=ServletActionContext.getResponse();
			resp.setContentType("application/x-excel");
			resp.setHeader("Content-Disposition", "attachment;filename="+new String("用户列表.xls".getBytes(),"ISO-8859-1"));
			ServletOutputStream out=resp.getOutputStream();
			userService.exportExcel(userService.findObjects(),out);
			if(out!=null){
				out.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//导入
	public String importExcel(){
		//strName=user.getName();
		//获取EXCEL
		if(userExcel!=null){
			//是否是excel
			if(userExcelFileName.matches("^.+\\.(?i)((xls)|(xlsx))$")){
				//导入
				userService.importExcel(userExcel,userExcelFileName);
			}
			
		}		
		return "list";
	}
	//校验用户账号唯一
	public void verifyAccount() {

		try {
			if(user!=null&&StringUtils.isNotBlank(user.getAccount())){
				System.out.println("==="+user.getId()+"===");
				List<User> list=userService.getUserByAccountAndId(user.getAccount(),user.getId());
				String strResult="true";
				if(list!=null&&list.size()>0){
					strResult="false";
				}
				HttpServletResponse resp=ServletActionContext.getResponse();
				resp.setContentType("text/html");
				ServletOutputStream out=resp.getOutputStream();
				out.write(strResult.getBytes()); 
				if(out!=null){
					out.close();
				}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}
	//*******************************************
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public File getHeadImg() {
		return headImg;
	}
	public void setHeadImg(File headImg) {
		this.headImg = headImg;
	}
	public String getHeadImgConyenType() {
		return headImgConyenType;
	}
	public void setHeadImgConyenType(String headImgConyenType) {
		this.headImgConyenType = headImgConyenType;
	}
	public String getHeadImgFileName() {
		return headImgFileName;
	}
	public void setHeadImgFileName(String headImgFileName) {
		this.headImgFileName = headImgFileName;
	}
	public File getUserExcel() {
		return userExcel;
	}
	public void setUserExcel(File userExcel) {
		this.userExcel = userExcel;
	}
	public String getUserExcelConyenType() {
		return userExcelConyenType;
	}
	public void setUserExcelConyenType(String userExcelConyenType) {
		this.userExcelConyenType = userExcelConyenType;
	}
	public String getUserExcelFileName() {
		return userExcelFileName;
	}
	public void setUserExcelFileName(String userExcelFileName) {
		this.userExcelFileName = userExcelFileName;
	}
	public String[] getUserRoleIds() {
		return userRoleIds;
	}
	public void setUserRoleIds(String[] userRoleIds) {
		this.userRoleIds = userRoleIds;
	}
	public String getStrName() {
		return strName;
	}
	public void setStrName(String strName) {
		this.strName = strName;
	}
	

}
