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
        <th><label for="status-input">Статус</label></th>
        <th><label for="nameTask-input">Наименование</label></th>
        <th><label for="job-input">Работа</label></th>
        <th><label for="startDate-input">Дата начала</label></th>
        <th><label for="finishDate-input">Дата окончания</label></th>
        <th><label for="personalName-input">Исполнитель</label></th>
        <th><a class="create"
               href="${pageContext.request.contextPath}/controller?command=show_create_task_page">Создать
            задачу</a></th>
    </tr>
    <c:forEach var="task" items="${requestScope.tasks}">
        <form name="task" action="${pageContext.request.contextPath}/controller?command=remove_task"
              method="post">
            <tr>
                <td><input type="hidden" name="id" value="${task.id}">
                    <input id="status-input" class="container" type="text" name="nameStatus" readonly
                           value="${task.status.nameStatus}"/></td>
                <td><input id="nameTask-input" class="container" type="text" name="name" readonly
                           value="${task.name}"/></td>
                <td><input id="job-input" class="container" type="text" name="job" readonly
                           value="${task.job}"/></td>
                <td><input id="startDate-input" class="container" type="text" name="startDate" readonly
                           value="${task.startDate}"/></td>
                <td><input id="finishDate-input" class="container" type="text" name="finishDate" readonly
                           value="${task.finishDate}"/></td>
                <td><input id="personalName-input" class="container" type="text" name="personalName" readonly
                           value="${task.employee.lastName} ${task.employee.firstName} ${task.employee.patronymic}"/>
                </td>
                <td>
                    <a class="create"
                       href="${pageContext.request.contextPath}/controller?command=show_update_task_page&id=${task.id}">
                        Изменить задачу</a>
                    <button type="submit" class="create">Удалить задачу</button>
                </td>
            </tr>
        </form>
    </c:forEach>
</table>
</body>
</html>