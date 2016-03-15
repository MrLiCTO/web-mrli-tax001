package com.shilong.home.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shilong.core.constant.Constant;
import com.shilong.core.util.QueryHelper;
import com.shilong.nsfw.complain.entity.Complain;
import com.shilong.nsfw.complain.service.ComplainService;
import com.shilong.nsfw.info.entity.Info;
import com.shilong.nsfw.info.service.InfoService;
import com.shilong.nsfw.user.entity.User;
import com.shilong.nsfw.user.service.UserService;

import net.sf.json.JSONObject;

public class HomeAction extends ActionSupport {
	@Resource
	private UserService userService;
	
	@Resource
	private ComplainService complainService;
	
	@Resource
	private InfoService infoService;
	
	private Map<String,Object> return_map;
	
	private Complain comp;
	
	private Info info;
	
	public String execute(){
		
		ActionContext.getContext().getContextMap().put("infoTypeMap", Info.INFO_TYPE_MAP);
		QueryHelper qh=new QueryHelper(Info.class,"i");
		qh.addOrderByProperty("i.createTime",QueryHelper.ORDER_BY_DESC);
		List<Info> infoList=infoService.getPageResult(qh, 1,5).getItems();
		ActionContext.getContext().getContextMap().put("infoList",infoList);
		
		ActionContext.getContext().put("complainStateMap",Complain.COMPLAIN_STATE_MAP);
		User u=(User) ServletActionContext.getRequest().getSession().getAttribute(Constant.USER);
		QueryHelper qh1=new QueryHelper(Complain.class,"c");
		qh1.addCondition("c.compName=?",u.getName());
		qh1.addOrderByProperty("c.compTime",QueryHelper.ORDER_BY_ASC);
		qh1.addOrderByProperty("c.state", QueryHelper.ORDER_BY_DESC);
		List<Info> compList=complainService.getPageResult(qh1, 1,7).getItems();
		ActionContext.getContext().getContextMap().put("complainList",compList);
		
		return "home";
	}
	
	public String complainAddUI(){
		return "complainAddUI";
	}
	
	public String complainViewUI(){
		ActionContext.getContext().put("complainStateMap",Complain.COMPLAIN_STATE_MAP);
		if(comp!=null){
			comp=complainService.findObjectById(comp.getCompId());
		}
		return "complainViewUI";
	}
	
	public String infoViewUI(){
		ActionContext.getContext().getContextMap().put("infoTypeMap", Info.INFO_TYPE_MAP);
		if(info!=null){
			info=infoService.findObjectById(info.getInfoId());
		}
		return "infoViewUI";
	}
	
	public void getUserJson(){
		try {
			String dept=ServletActionContext.getRequest().getParameter("dept");
			if(StringUtils.isNotBlank(dept)){
				QueryHelper qh=new QueryHelper(User.class,"u");
				
				qh.addCondition("u.dept like ?",dept);
				
				List<User> userList=userService.findObjects(qh);
				
				JSONObject json=new JSONObject();
				
				json.put("msg","success");
				
				json.accumulate("userList",userList);
				
				HttpServletResponse resp = ServletActionContext.getResponse();
				resp.setContentType("text/html");
				ServletOutputStream out = resp.getOutputStream();
				
				out.write(json.toString().getBytes("utf-8"));

				out.close();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public String getUserJson1(){
		try {
			String dept=ServletActionContext.getRequest().getParameter("dept");
			if(StringUtils.isNotBlank(dept)){
				QueryHelper qh=new QueryHelper(User.class,"u");
				
				qh.addCondition("u.dept like ?","%"+dept);
				
				List userList=userService.findObjects(qh);
				
				return_map=new HashMap<String,Object>();
				
				return_map.put("userList",userList);
				
				return_map.put("msg","success");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public void complainAdd(){
		try {
			if(comp!=null){
				comp.setState(Complain.COMPLAIN_STATE_UNDONE);
				comp.setCompTime(new Timestamp(new Date().getTime()));
				complainService.save(comp);
				
				HttpServletResponse resp = ServletActionContext.getResponse();
				resp.setContentType("text/html");
				ServletOutputStream out = resp.getOutputStream();
				
				out.write("success".getBytes("utf-8"));

				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public Map<String, Object> getReturn_map() {
		return return_map;
	}

	public void setReturn_map(Map<String, Object> return_map) {
		this.return_map = return_map;
	}

	public Complain getComp() {
		return comp;
	}

	public void setComp(Complain comp) {
		this.comp = comp;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	
	
	
}
