<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создание проекта</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/styleForObject.css"%>
    <%@include file="/WEB-INF/css/text.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/Header.jsp" %>
<h2>Создание проекта</h2>
<form name="project-form" action="${pageContext.request.contextPath}/controller?command=create_project"
      method="post">
    <label for="projectName-input">Название проекта</label>
    <input id="projectName-input" class="container" type="text" name="name" pattern="^.{1,40}$" readonly/>
    <label for="projectDescription-input">Описание проекта</label>
    <input id="projectDescription-input" class="container" type="text" name="description" pattern="^.{1,40}$" readonly/>
    <button type="submit" class="button">Создать проект</button>
    <a href="${pageContext.request.contextPath}/controller?command=project_page">
        Отменить</a>
</form>
</body>
</html>
