package com.shilong.core.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class QueryHelper {
	
	private List<Object> parameters;
	
	private String fromClause="";
	
	private String whereClause="";
	
	private String orderByClause="";
	
	public final static String ORDER_BY_DESC="desc";
	public final static String ORDER_BY_ASC="asc";
	
	public QueryHelper(Class clazz,String alias) {
		fromClause="from "+clazz.getSimpleName()+" "+alias;
	}
	
	public void addCondition(String condition,Object... params){
		if(whereClause.length()>1){
			whereClause+=" and "+condition;
			
		}else{
			whereClause=" where "+condition;
		}
		
		if(parameters==null){
			parameters=new ArrayList<Object>();
			if(params!=null){
				for(Object obj:params){
					parameters.add(obj);
				}
			}
		}else{
			if(params!=null){
				for(Object obj:params){
					parameters.add(obj);
				}
			}
		}

	}
	
	
	public void addOrderByProperty(String property,String order){
		if(orderByClause.length()>1){
			
			orderByClause+=" , "+property+" "+order;
			
		}else{
			
			orderByClause=" order by "+property+" "+order;
		}
	}
	
	
	public String getQueryListHql(){
		return fromClause+whereClause+orderByClause;
		
	}
	
	public List<Object> getParameters(){
		return parameters;
	}

	public String getQueryCountHql() {
		return "select count(*) "+fromClause+whereClause;
	}
}
