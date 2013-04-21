<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add User</title>
</head>
<body>
	<s:form action="user-add" method="post" validate="true" namespace="/">
		<s:textfield name="id" label="Id"></s:textfield>
		<s:password name="password" label="Password"></s:password>
		<s:textfield name="name" label="Name"></s:textfield>
		<s:submit></s:submit>
	</s:form>
</body>
</html>