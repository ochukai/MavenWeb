package com.onakaumi.akiumi.web.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.onakaumi.akiumi.model.User;
import com.onakaumi.akiumi.service.UserService;
import com.onakaumi.core.base.service.IService;
import com.onakaumi.core.base.web.action.BaseAction;

/**
 * @author Onakaumi
 * @since 2013-4-14 下午11:38:55
 * 
 */
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User, String> {
	private static final long serialVersionUID = 1L;
	
	private UserService userSerice;

	@Resource
	public void setUserSerice(UserService userSerice) {
		this.userSerice = userSerice;
	}

	@Override
	protected IService<User, String> service() {
		return userSerice;
	}

}
