<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>
	<form:form>
		帳號：<form:input path="userName" /> <br>
		密碼：<form:password path="password" /><br>
		Email：<form:input path="email" /><br>
		<input type="submit" value="注册" name="testSubmit"/>
		<input type="reset" value="重置" />
	</form:form>
</body>
</html>