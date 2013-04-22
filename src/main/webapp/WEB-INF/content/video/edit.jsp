<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Video</title>
</head>
<body>
	<s:form action="video-update" method="post" namespace="/">
		<s:textfield name="name" label="Name" />
		<s:textfield name="path" label="Path" />
		<s:textfield name="description" label="Desc" />
		<s:textfield name="hits" label="Hits" />
		<s:submit/>
	</s:form>
</body>
</html>