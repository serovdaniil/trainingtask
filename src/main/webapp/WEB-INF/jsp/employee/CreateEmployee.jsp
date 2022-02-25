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
<form name="question-form" action="${pageContext.request.contextPath}/controller?command=create_project"
      method="post">
    <label for="projectName-input">Название проекта</label>
    <input id="projectName-input" class="container" type="text" name="name" />
    <label for="projectDescription-input">Описание проекта</label>
    <input id="projectDescription-input" class="container" type="text" name="description" />
    <button type="submit" class="button">Создать проект</button>
</form>
</body>
</html>
