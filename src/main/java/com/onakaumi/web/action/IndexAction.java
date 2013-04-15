package com.onakaumi.web.action;

import org.springframework.stereotype.Controller;

@Controller
public class IndexAction {
	public String index(){
		System.out.println("index``````````````````");
		return "success";
	}
}
