/**
 * 
 */
package com.onakaumi.akiumi.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.onakaumi.core.base.model.AbstractModel;

/**
 * @author Onakaumi
 * @since 2013-4-14 下午11:38:43
 * 
 */
@Entity
@Table(name = "aki_user")
public class User extends AbstractModel<String> {
	private static final long serialVersionUID = 1L;

	private String id;

	private String password;

	private String name;

	private Date lastLogin;

	/**
	 * 
	 */
	public User() {
		super();
	}

	/**
	 * @param id
	 * @param password
	 * @param name
	 */
	public User(String id, String password, String name) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
	}

	/**
	 * @return the id
	 */
	@Override
	@Id
	public String getId() {
		return id;
	}

	/**
	 * @return the lastLogin
	 */
	public Date getLastLogin() {
		return lastLogin;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	@Override
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param lastLogin
	 *            the lastLogin to set
	 */
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
