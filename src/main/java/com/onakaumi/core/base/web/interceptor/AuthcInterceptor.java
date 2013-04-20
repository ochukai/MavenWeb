package com.onakaumi.core.base.web.interceptor;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @author onakaumi
 * 
 */
@Component("authcInterceptor")
public class AuthcInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(getClass());

	/**
	 * @param invocation
	 * @return
	 * @throws NoSuchMethodException
	 */
	private Method getExecuteMethod(ActionInvocation invocation)
			throws NoSuchMethodException {
		Class<?> actionClz = invocation.getAction().getClass(); // 请求的action
		String methodName = invocation.getProxy().getMethod(); // 方法名称
		return actionClz.getMethod(methodName); // 得到请求的方法。
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("authing .............");
		}
		Method method = getExecuteMethod(invocation);
		if (logger.isDebugEnabled()) {
			logger.debug("authc method: " + method);
		}
		return invokeAuthAndReturn(invocation, method);

	}

	/**
	 * @param invocation
	 * @param method
	 * @return
	 * @throws Exception
	 */
	private String invokeAuthAndReturn(ActionInvocation invocation,
			Method method) throws Exception {
		return invocation.invoke();
	}

}