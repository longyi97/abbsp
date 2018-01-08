<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统出错</title>
</head>
<body>
<h1>出错了！！</h1><br>
<c:forEach var="constraintViolation" items="${constraintViolations }">
${constraintViolation.propertyPath} | ${constraintViolation.message}<br>
</c:forEach>
<br>
<h1>${error_message }</h1>
</body>
</html>