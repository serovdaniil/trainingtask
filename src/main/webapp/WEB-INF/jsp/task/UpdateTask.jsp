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
<form name="project-form" action="${pageContext.request.contextPath}/controller?command=update_project"
      method="post">
    <label for="projectId-input">ID:</label>
    <input id="projectId-input" class="container" type="text" name="id" readonly
           value="${requestScope.project.id}"/>
    <label for="projectName-input">Название:</label>
    <input id="projectName-input" class="container" type="text" name="name"
           pattern="^.{1,40}$" value="${requestScope.project.name}"/>
    <label for="projectDescription-input">Описание:</label>
    <input id="projectDescription-input" class="container" type="text" name="description"
           pattern="^.{1,40}$" value="${requestScope.project.description}"/>
    <button type="submit" class="button">Изменить</button>
    <a href="${pageContext.request.contextPath}/controller?command=show_update_project_page&id=${requestScope.project.id}">
        Отменить</a>
</form>
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
            проект</a></th>
    </tr>
    <c:forEach var="task" items="${requestScope.tasks}">
        <form name="task" action="${pageContext.request.contextPath}/controller?command=remove_project"
              method="post">
            <tr>
                <td><input class="container" type="text" name="id" readonly
                           value="${task.status.nameStatus}" /></td>
                <td><input class="container" type="text" name="id" readonly
                           value="${task.name}"/></td>
                <td><input class="container" type="text" name="id" readonly
                           value="${task.job}"/></td>
                <td><input class="container" type="text" name="id" readonly
                           value="${task.startDate}"/></td>
                <td><input class="container" type="text" name="id" readonly
                           value="${task.finishDate}"/></td>
                <td><input class="container" type="text" name="id" readonly
                           value="${task.employee.lastName} ${task.employee.firstName} ${task.employee.patronymic}"/></td>
                <td>
                    <a class="create"
                       href="${pageContext.request.contextPath}/controller?command=show_update_project_page&id=${task.id}">
                        Изменить проект</a>
                    <button type="submit" class="create">Удалить проект</button>
                </td>
            </tr>
        </form>
    </c:forEach>
</table>
</body>
</html>
