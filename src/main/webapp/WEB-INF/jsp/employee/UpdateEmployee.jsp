<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Обновление проекта</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/styleForObject.css"%>
    <%@include file="/WEB-INF/css/text.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/Header.jsp" %>
<h2>Изменение сотрудника </h2>
<form name="question-form" action="${pageContext.request.contextPath}/controller?command=update_employee"
      method="post">
    <label for="employeeFirstName-input">Имя:</label>
    <input id="employeeFirstName-input" class="container" type="text" name="firstName"
           pattern="^.{1,40}$" value="${requestScope.employee.firstName}" readonly/>
    <label for="employeeLastName-input">Фамилия:</label>
    <input id="employeeLastName-input" class="container" type="text" name="lastName"
           pattern="^.{1,40}$" value="${requestScope.employee.lastName}" readonly/>
    <label for="employeePatronymic-input">Отчество:</label>
    <input id="employeePatronymic-input" class="container" type="text" name="patronymic"
           pattern="^.{1,40}$" value="${requestScope.employee.patronymic}" readonly/>
    <label for="employeePosition-input">Должность:</label>
    <input id="employeePosition-input" class="container" type="text" name="position"
           pattern="^.{1,40}$" value="${requestScope.employee.position}" readonly/>
    <input type="hidden" name="id" value="${requestScope.employee.id}">
    <button type="submit" class="button">Обновить сотрудника</button>
    <a href="${pageContext.request.contextPath}/controller?command=employee_page">
        Отменить</a>
</form>
</body>
</html>
