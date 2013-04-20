package com.onakaumi.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.onakaumi.akiumi.model.User;
import com.onakaumi.util.constant.Constants;

public class SessionUtil {

	/**
	 * 从session 取出user
	 * 
	 * @return
	 */
	public static User getCurrentUserFromSession() {
		HttpServletRequest req = ServletActionContext.getRequest();
		Object o = req.getSession().getAttribute(Constants.CURRENT_USER);
		return (o != null) ? (User) o : null;
	}

}
