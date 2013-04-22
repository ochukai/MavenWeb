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
		<a href="video-editNew">Add Video</a>
	</h1>
	
	<s:iterator value="pager.list">
		<s:property value="name" />-
		<a href="video-show?id=${id }"><s:property value="id" /></a>-
		<s:property value="description" />-
		<s:property value="path" />-
		<s:property value="hits" />-
		
		<a href="video-edit?id=${id }">Edit</a>-
		<a href="video-delete?id=${id }">Delete</a>
		<br />
	</s:iterator>

</body>
</html>