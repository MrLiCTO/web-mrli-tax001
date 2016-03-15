package com.shilong.nsfw.info.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.shilong.core.action.BaseAction;
import com.shilong.core.constant.Constant;
import com.shilong.core.page.PageResult;
import com.shilong.core.util.QueryHelper;
import com.shilong.nsfw.info.entity.Info;
import com.shilong.nsfw.info.service.InfoService;

public class InfoAction extends BaseAction {
	@Resource
	private InfoService infoService;
	private Info info;
	private String strTitle;
	
	
	
	// 列表页面
	public String listUI() throws Exception {
		ActionContext.getContext().getContextMap().put("infoTypeMap", Info.INFO_TYPE_MAP);
		
		QueryHelper qh = new QueryHelper(Info.class, "i");
		
		//String hql = qh.getQueryListHql();

		//List<Object> parameters = qh.getParameters();

		if (info != null) {
			
			info.setTitle(URLDecoder.decode(info.getTitle(),"utf-8"));

			qh.addCondition(" i.title like ?", "%" + info.getTitle() + "%");
			
			qh.addCondition(" i.state= ? ","1");
			
		}
		
		qh.addOrderByProperty(" i.createTime ", QueryHelper.ORDER_BY_DESC);

		// String hql=" from Info i ";
		//
		//
		//
		// if(info!=null){
		// if(StringUtils.isNotBlank(info.getTitle())){
		// hql+=" where i.title like ? ";
		// parameters.add("%"+info.getTitle()+"%");
		// }
		// }
		//
		// hql+=" order by i.createTime desc ";

		//infoList = infoService.findObjects(hql, parameters);
		
		//infoList = infoService.findObjects(q);
		
		pageResult=infoService.getPageResult(qh,getPageNo(),getPageSize());

		return "listUI";
	}

	// 跳转到新增页面
	public String addUI() {
		ActionContext.getContext().getContextMap().put("infoTypeMap", Info.INFO_TYPE_MAP);

		//info = new Info();// ！！！！！！！！！！！！！！！！！！！！！！！
		
		strTitle=info.getTitle();
		
		info=new Info();

		info.setCreateTime(new Timestamp(new Date().getTime()));
		
		
		return "addUI";
	}

	// 保存新增
	public String add() {

		infoService.save(info);

		return "list";
	}

	// 跳转到编辑页面
	public String editUI() {
		ActionContext.getContext().getContextMap().put("infoTypeMap", Info.INFO_TYPE_MAP);
		if (info != null && info.getInfoId() != null) {
			strTitle=info.getTitle();
			info = infoService.findObjectById(info.getInfoId());

		}

		return "editUI";
	}

	// 保存编辑
	public String edit() {

		infoService.update(info);
		
		return "list";
	}

	// 删除
	public String delete() {
		if (info != null && info.getInfoId() != null) {
			strTitle=info.getTitle();
			infoService.delete(info.getInfoId());
		}
		return "list";
	}

	// 批量删除
	public String deleteSelected() {
		strTitle=info.getTitle();
		if (selectedRow != null) {
			
			for (String id : selectedRow) {
				infoService.delete(id);
			}
		}
		return "list";
	}

	public void infoPublic() {
		if (info != null) {
			Info item = infoService.findObjectById(info.getInfoId());
			item.setState(info.getState());
			infoService.update(item);

			try {
				HttpServletResponse resp = ServletActionContext.getResponse();
				resp.setContentType("text/html");
				ServletOutputStream out = resp.getOutputStream();

				out.write("更新状态成功".getBytes("utf-8"));

				// out.write("true".getBytes("utf-8"));

				out.close();

			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	}

	// *******************************************

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public String getStrTitle() {
		return strTitle;
	}

	public void setStrTitle(String strTitle) {
		this.strTitle = strTitle;
	}
	
}
