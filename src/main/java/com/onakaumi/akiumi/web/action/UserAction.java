/**
 * 
 */
package com.onakaumi.akiumi.web.action;

import java.util.ArrayList;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.onakaumi.akiumi.model.User;
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

	public UserAction() {
		lists = new ArrayList<>();
		lists.add(new User("xiaoyee", "123", "wangzhonghai"));
	}

	@Override
	public String index() {
		return super.index();
	}

	@Override
	public void prepare() throws Exception {
		model = new User();
	}

	@Override
	public String add() {
		log.debug(model);
		lists.add(model);
		return super.add();
	}

}
