<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Создание задачи</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/styleForObject.css"%>
    <%@include file="/WEB-INF/css/text.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/Header.jsp" %>
<h2>Создание задачи</h2>
<form name="task" action="${pageContext.request.contextPath}/controller?command=create_task"
      method="post">
    <label for="taskName-input">Название задачи</label>
    <input id="taskName-input" class="container" type="text" name="name"/>
    <label for="projectName-input">Название проекта</label>
    <select id="projectName-input" name="nameProject">
        <c:forEach var="project" items="${requestScope.projects}">
            <option value="${project.id}"> ${project.name} </option>
        </c:forEach>
    </select>
    <label for="taskJob-input">Сколько требуется времени на выполнение</label>
    <input id="taskJob-input" class="container" type="text" name="job"/>
    <label for="taskStartDate-input">Дата начала</label>
    <input id="taskStartDate-input" class="container" type="text" name="startDate"/>
    <label for="taskFinishDate-input">Дата окончания</label>
    <input id="taskFinishDate-input" class="container" type="text" name="finishDate"/>
    <label for="taskStatus-input">Статус задачи</label>
    <select id="taskStatus-input" name="nameStatus">
        <option value="1"> Не начата</option>
        <option value="2"> В процессе</option>
        <option value="3"> Завершена</option>
        <option value="4"> Отложена</option>
    </select>
    <label for="taskPersonalName-input">Исполнитель</label>
    <select id="taskPersonalName-input" name="idEmployee">
        <c:forEach var="employee" items="${requestScope.employees}">
            <option value="${employee.id}">${employee.lastName} ${employee.firstName} ${employee.patronymic}</option>
        </c:forEach>
    </select>
    <button type="submit" class="create">Создать задачу</button>
</form>
</body>
</html>
