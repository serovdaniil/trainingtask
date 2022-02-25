<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создание сотрудника</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/styleForObject.css"%>
    <%@include file="/WEB-INF/css/text.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/Header.jsp" %>
<h2>Создание сотрудника</h2>
<form name="employee-form" action="${pageContext.request.contextPath}/controller?command=create_employee"
      method="post">
    <label for="employeeFirstName-input">Имя:</label>
    <input id="employeeFirstName-input" class="container" type="text" name="firstName" pattern="^.{1,40}$"/>
    <label for="employeeLastName-input">Фамилия:</label>
    <input id="employeeLastName-input" class="container" type="text" name="lastName" pattern="^.{1,40}$"/>
    <label for="employeePatronymic-input">Отчество:</label>
    <input id="employeePatronymic-input" class="container" type="text" name="patronymic" pattern="^.{1,40}$"/>
    <label for="employeePosition-input">Должность:</label>
    <input id="employeePosition-input" class="container" type="text" name="position" pattern="^.{1,40}$"/>
    <button type="submit" class="button">Создать сотрудника</button>
</form>
</body>
</html>
