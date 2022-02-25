<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список задач</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/tableStyle.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/Header.jsp" %>
<br>
<table>
    <tr>
        <th>Статус</th>
        <th>Наименование</th>
        <th>Работа</th>
        <th>Дата начала</th>
        <th>Дата окончания</th>
        <th>Исполнитель</th>
        <th><a class="create"
               href="${pageContext.request.contextPath}/controller?command=show_create_project_page">Создать
            задачу</a></th>
    </tr>
    <c:forEach var="task" items="${requestScope.tasks}">
        <form name="task" action="${pageContext.request.contextPath}/controller?command=remove_task&id=${task.id}"
              method="post">
            <tr>
                <td><input class="container" type="text" name="nameStatus" readonly
                           value="${task.status.nameStatus}"/></td>
                <td><input class="container" type="text" name="name" readonly
                           value="${task.name}"/></td>
                <td><input class="container" type="text" name="job" readonly
                           value="${task.job}"/></td>
                <td><input class="container" type="text" name="startDate" readonly
                           value="${task.startDate}"/></td>
                <td><input class="container" type="text" name="finishDate" readonly
                           value="${task.finishDate}"/></td>
                <td><input class="container" type="text" name="personalName" readonly
                           value="${task.employee.lastName} ${task.employee.firstName} ${task.employee.patronymic}"/></td>
                <td>
                    <a class="create"
                       href="${pageContext.request.contextPath}/controller?command=show_update_project_page&id=${task.id}">
                        Изменить задачу</a>
                    <button type="submit" class="create">Удалить задачу</button>
                </td>
            </tr>
        </form>
    </c:forEach>
</table>
</body>
</html>