<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список сотрудников</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/dataListStyle.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/Header.jsp" %>
<a class="buttonMore" href="${pageContext.request.contextPath}/controller?command=show_create_project_page">Создать проект</a>
<ul class="list3a">
    <c:forEach var="employee" items="${requestScope.employees}">
        <form name="employee" action="${pageContext.request.contextPath}/controller?command=remove_project"
              method="post">
            <li>
                <label for="employeeId-input">ID:</label>
                <input id="employeeId-input" class="container" type="text" name="id" readonly
                       value="${employee.id}"/>
                <label for="employeeFirstName-input">Имя:</label>
                <input id="employeeFirstName-input" class="container" type="text" name="firstName" readonly
                       value="${employee.firstName}"/>
                <label for="employeeLastName-input">Фамилия:</label>
                <input id="employeeLastName-input" class="container" type="text" name="lastName" readonly
                       value="${employee.lastName}"/>
                <label for="employeePatronymic-input">Отчество:</label>
                <input id="employeePatronymic-input" class="container" type="text" name="patronymic" readonly
                       value="${employee.patronymic}"/>
                <label for="employeePosition-input">Должность:</label>
                <input id="employeePosition-input" class="container" type="text" name="position" readonly
                       value="${employee.position}"/>
                <button type="submit" class="button">Удалить</button>
                <a class="buttonMore" href="${pageContext.request.contextPath}/controller?command=show_update_project_page&id=${project.id}">
                    Изменить проект</a>
            </li>
        </form>
    </c:forEach>
</ul>
</body>
</html>