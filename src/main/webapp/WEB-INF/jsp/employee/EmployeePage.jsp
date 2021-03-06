<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список сотрудников</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/tableStyle.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/Header.jsp" %>
<br>
<table>
    <tr>
        <th><label for="employeeFirstName-input">Имя</label></th>
        <th><label for="employeeLastName-input">Фамилия</label></th>
        <th><label for="patronymic-input">Отчество</label></th>
        <th><label for="position-input">Должность</label></th>
        <th><a class="create"
               href="${pageContext.request.contextPath}/controller?command=show_create_employee_page">Создать
            сотрудника</a></th>
    </tr>
    <c:forEach var="employee" items="${requestScope.employees}">
        <form name="employee" action="${pageContext.request.contextPath}/controller?command=remove_employee"
              method="post">
            <tr>
                <td><input id="employeeFirstName-input" class="container" type="text" name="firstName" readonly
                           value="${employee.firstName}"/></td>
                <td><input id="employeeLastName-input" class="container" type="text" name="lastName" readonly
                           value="${employee.lastName}"/></td>
                <td><input id="patronymic-input" class="container" type="text" name="patronymic" readonly
                           value="${employee.patronymic}"/></td>
                <td><input id="position-input" class="container" type="text" name="position" readonly
                           value="${employee.position}"/></td>
                <td>
                    <input type="hidden" name="id" value="${employee.id}">
                    <a class="create"
                       href="${pageContext.request.contextPath}/controller?command=show_update_employee_page&id=${employee.id}">
                        Изменить сотрудника</a>
                    <button type="submit" class="create">Удалить сотрудника</button>
                </td>
            </tr>
        </form>
    </c:forEach>
</table>
</body>
</html>