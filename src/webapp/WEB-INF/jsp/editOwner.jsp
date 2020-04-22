<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<form:form modelAttribute="owner" method="post">
    Fist Name:<form:input path="firstName"></form:input>
    <form:errors path="firstName"></form:errors>
    <br>
    Last Name:<form:input path="lastName"></form:input>
    <form:errors path="lastName"></form:errors>

    <form:button name="submit">Update</form:button>

</form:form>
</body>
</html>