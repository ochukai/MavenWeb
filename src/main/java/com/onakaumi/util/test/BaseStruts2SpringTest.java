package com.onakaumi.util.test;

import org.apache.struts2.StrutsSpringTestCase;

public class BaseStruts2SpringTest extends StrutsSpringTestCase {

	/**
	 * spring 的配置文件位置。 
	 * 
	 * 现在还不知道怎么制定struts2 的配置文件的地址, 测试时struts.xml 放在src下面
	 * 
	 */
	@Override
	protected String[] getContextLocations() {
		return new String[] { "/resources/spring/applicationContext.xml" };
	}

}
