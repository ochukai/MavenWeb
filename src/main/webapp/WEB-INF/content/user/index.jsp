<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User list</title>
</head>
<body>
	<h1>
		<a href="user-editNew">Add User</a>
	</h1>

	<s:iterator value="pager.list">
		<s:property value="name" />-<a href="user-show?id=${id }"><s:property
				value="id" /></a>-<s:property value="password" />-
				<a href="user-edit?id=${id }">Edit</a>-
				<a href="user-delete?id=${id }">Delete</a>
		<br />
	</s:iterator>

</body>
</html>