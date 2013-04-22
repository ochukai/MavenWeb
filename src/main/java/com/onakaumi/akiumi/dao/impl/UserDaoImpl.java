/**
 * 
 */
package com.onakaumi.akiumi.dao.impl;

import org.springframework.stereotype.Repository;

import com.onakaumi.akiumi.dao.UserDao;
import com.onakaumi.akiumi.model.User;
import com.onakaumi.core.base.dao.hibernate.GenericDAOHibernateImpl;

/**
 * @author Onakaumi
 * @since 2013-4-23 上午12:24:35
 * 
 */
@Repository("userDao")
public class UserDaoImpl extends GenericDAOHibernateImpl<User, String>
		implements UserDao {

}
