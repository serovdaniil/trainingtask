<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/headerStyle.css"%>
</style>
<ul id="menu">
    <li><a href="${pageContext.request.contextPath}/controller?command=project_page">Проекты</a></li>
    <li><a href="${pageContext.request.contextPath}/controller?command=employee_page">Сотрудники</a></li>
    <li><a href="${pageContext.request.contextPath}/controller?command=task_page">Задачи</a></li>
</ul>
</body>
</html>
