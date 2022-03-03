<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<c:if test="${not empty requestScope.exception}">
    <p><b>${requestScope.exception}</b></p>
    <br>
</c:if>
<form name="employee-form" action="${pageContext.request.contextPath}/controller?command=create_employee"
      method="post">
    <label for="employeeFirstName-input">Имя:</label>
    <input id="employeeFirstName-input" class="container" type="text" name="firstName" pattern="^.{1,40}$" readonly/>
    <label for="employeeLastName-input">Фамилия:</label>
    <input id="employeeLastName-input" class="container" type="text" name="lastName" pattern="^.{1,40}$" readonly/>
    <label for="employeePatronymic-input">Отчество:</label>
    <input id="employeePatronymic-input" class="container" type="text" name="patronymic" pattern="^.{1,40}$" readonly/>
    <label for="employeePosition-input">Должность:</label>
    <input id="employeePosition-input" class="container" type="text" name="position" pattern="^.{1,40}$"readonly/>
    <button type="submit" class="button">Создать сотрудника</button>
    <a href="${pageContext.request.contextPath}/controller?command=employee_page">
        Отменить</a>
</form>
</body>
</html>
