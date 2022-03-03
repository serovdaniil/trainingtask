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
<h2>Изменение задачи</h2>
<form name="project-form"
      action="${pageContext.request.contextPath}/controller?command=update_task"
      method="post">
    <input type="hidden" name="id" value="${requestScope.task.id}">
    <input type="hidden" name="idProject" value="${requestScope.task.project.id}">
    <label for="taskName-input">Название задачи</label>
    <input id="taskName-input" class="container" type="text" name="name"
           value="${requestScope.task.name}" readonly/>
    <label for="projectName-input">Название проекта</label>
    <input id="projectName-input" class="container" type="text" name="nameProject"
           value="${requestScope.task.project.nameProject}" readonly/>
    <label for="taskJob-input">Сколько требуется времени на выполнение</label>
    <input id="taskJob-input" class="container" type="number" name="job"
           value="${requestScope.task.job}" readonly/>
    <label for="taskStartDate-input">Дата начала</label>
    <input id="taskStartDate-input" class="container" type="text" name="startDate"
           value="${requestScope.task.startDate}" readonly/>
    <label for="taskFinishDate-input">Дата окончания</label>
    <input id="taskFinishDate-input" class="container" type="text" name="finishDate"
           value="${requestScope.task.finishDate}" readonly/>
    <label for="taskStatus-input">Статус задачи</label>
    <select id="taskStatus-input" name="nameStatus">
        <%-- <option>На данный момент - ${requestScope.task.status.nameStatus}</option>--%>
        <option value="1"> Не начата</option>
        <option value="2"> В процессе</option>
        <option value="3"> Завершена</option>
        <option value="4"> Отложена</option>
    </select>
    <label for="taskPersonalName-input">Исполнитель</label>
    <select id="taskPersonalName-input" name="idEmployee">
        <%--   <option>На данный момент -
               ${requestScope.task.employee.lastName} ${requestScope.task.employee.firstName}
               ${requestScope.task.employee.patronymic}</option>--%>
        <c:forEach var="employee" items="${requestScope.employees}">
            <option value="${employee.id}">${employee.lastName} ${employee.firstName} ${employee.patronymic}</option>
        </c:forEach>
    </select>
    <button type="submit" class="button">Изменить задачу</button>
    <a href="${pageContext.request.contextPath}/controller?command=task_page">
        Отменить</a>
</form>
</body>
</html>
