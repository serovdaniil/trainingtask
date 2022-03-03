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
    <%@include file="/WEB-INF/css/tableStyle.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/Header.jsp" %>
<h2>Изменение проекта</h2>
<form name="project-form"
      action="${pageContext.request.contextPath}/controller?command=update_project&id=${requestScope.project.id}"
      method="post">
    <label for="projectName-input">Название:</label>
    <input id="projectName-input" class="container" type="text" name="name"
           pattern="^.{1,40}$" value="${requestScope.project.nameProject}"/>
    <label for="projectDescription-input">Описание:</label>
    <input id="projectDescription-input" class="container" type="text" name="description"
           pattern="^.{1,40}$" value="${requestScope.project.description}"/>
    <button type="submit" class="button">Изменить</button>
    <a href="${pageContext.request.contextPath}/controller?command=project_page">
        Отменить</a>
</form>
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
               href="${pageContext.request.contextPath}/controller?command=show_create_task_in_project_page&idProjectForNewTask./=${requestScope.project.id}">Создать
            задачу</a>
        </th>
    </tr>
    <c:forEach var="task" items="${requestScope.tasks}">
        <form name="task" action="${pageContext.request.contextPath}/controller?command=remove_task"
              method="post">
            <tr>
                <td><input type="hidden" name="id" value="${task.id}">
                    <input id="status-input" class="container" type="text" name="status" readonly
                           value="${task.status.nameStatus}"/></td>
                <td><input id="nameTask-input" class="container" type="text" name="id" readonly
                           value="${task.name}"/></td>
                <td><input id="job-input" class="container" type="text" name="id" readonly
                           value="${task.job}"/></td>
                <td><input id="startDate-input" class="container" type="text" name="id" readonly
                           value="${task.startDate}"/></td>
                <td><input id="finishDate-input" class="container" type="text" name="id" readonly
                           value="${task.finishDate}"/></td>
                <td><input id="personalName-input" class="container" type="text" name="id" readonly
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
