<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список проектов</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/dataListStyle.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/Header.jsp" %>
<a class="buttonMore" href="${pageContext.request.contextPath}/controller?command=show_create_project_page">Создать проект</a>
<ul class="list3a">
    <c:forEach var="project" items="${requestScope.projects}">
        <form name="project" action="${pageContext.request.contextPath}/controller?command=remove_project"
              method="post">
            <li>
                <label for="projectId-input">ID:</label>
                <input id="projectId-input" class="container" type="text" name="id" readonly
                       value="${project.id}"/>
                <label for="projectName-input">Название:</label>
                <input id="projectName-input" class="container" type="text" name="id" readonly
                       value="${project.name}"/>
                <label for="projectDescription-input">Описание:</label>
                <input id="projectDescription-input" class="container" type="text" name="id" readonly
                       value="${project.description}"/>
                <button type="submit" class="button">Удалить</button>
                <a class="buttonMore" href="${pageContext.request.contextPath}/controller?command=show_update_project_page&id=${project.id}">
                    Изменить проект</a>
            </li>
        </form>
    </c:forEach>
</ul>
</body>
</html>