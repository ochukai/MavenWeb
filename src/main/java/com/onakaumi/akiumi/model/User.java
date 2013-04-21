/**
 * 
 */
package com.onakaumi.akiumi.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

	@NotNull(message = "用户名不能为空！")
	@Size(max = 16, min = 5, message = "5-16")
	private String id;

	@Size(max = 16, min = 5, message = "")
	private String password;

	@NotNull
	@Size(min = 2, max = 6)
	private String name;
	
	private Date lastLogin;

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
	 * 
	 */
	public User() {
		super();
	}

	/**
	 * @return the id
	 */
	@Id
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the lastLogin
	 */
	public Date getLastLogin() {
		return lastLogin;
	}

	/**
	 * @param lastLogin
	 *            the lastLogin to set
	 */
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

}
