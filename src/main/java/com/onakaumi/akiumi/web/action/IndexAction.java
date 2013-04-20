package com.onakaumi.akiumi.web.action;

import org.springframework.stereotype.Controller;

@Controller
public class IndexAction {

	public String hello() {
		System.out.println("index``````````````````");
		return "success";
	}
}
