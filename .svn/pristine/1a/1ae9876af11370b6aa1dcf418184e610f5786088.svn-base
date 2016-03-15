package com.shilong.test.action;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionSupport;
import com.shilong.test.service.TestService;

public class TestAction extends ActionSupport {
	private static final long serialVersionUID = -8971573365315371833L;
	@Resource
	private TestService testService;
	public String execute() {
		testService.say();
		return SUCCESS;
	}
	
	
}
