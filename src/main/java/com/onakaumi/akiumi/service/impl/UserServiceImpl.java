/**
 * 
 */
package com.onakaumi.akiumi.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.onakaumi.akiumi.dao.UserDao;
import com.onakaumi.akiumi.model.User;
import com.onakaumi.akiumi.service.UserService;
import com.onakaumi.core.base.dao.IGenericDAO;
import com.onakaumi.core.base.service.AbstractService;

/**
 * @author Onakaumi
 * @since 2013-4-23 上午12:21:39
 * 
 */
@Service("userService")
public class UserServiceImpl extends AbstractService<User, String> implements
		UserService {

	private UserDao userDao;

	@Override
	protected IGenericDAO<User, String> dao() {
		return userDao;
	}

	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
