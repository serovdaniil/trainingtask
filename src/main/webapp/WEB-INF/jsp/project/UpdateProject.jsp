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
</style>
<%@include file="/WEB-INF/jsp/common/Header.jsp" %>
<h2>Изменение проекта</h2>
    <form name="question-form" action="${pageContext.request.contextPath}/controller?command=update_project"
          method="post">
        <label for="projectId-input">ID:</label>
        <input id="projectId-input" class="container" type="text" name="id" readonly
               value="${requestScope.project.id}"/>
        <label for="projectName-input">Название:</label>
        <input id="projectName-input" class="container" type="text" name="name"
               value="${requestScope.project.name}"/>
        <label for="projectDescription-input">Описание:</label>
        <input id="projectDescription-input" class="container" type="text" name="description"
               value="${requestScope.project.description}"/>
        <button type="submit" class="button">Изменить</button>
    </form>
</body>
</html>
