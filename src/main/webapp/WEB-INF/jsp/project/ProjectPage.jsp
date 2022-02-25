<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список проектов</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/tableStyle.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/Header.jsp" %>
<br>
<table>
    <tr>
        <th>Название</th>
        <th>Описание</th>
        <th><a class="create"
               href="${pageContext.request.contextPath}/controller?command=show_create_project_page">Создать
            проект</a></th>
    </tr>
    <c:forEach var="project" items="${requestScope.projects}">
        <form name="project" action="${pageContext.request.contextPath}/controller?command=remove_project"
              method="post">
            <tr>
                <td><input class="container" type="text" name="name" readonly
                           value="${project.name}"/></td>
                <td><input class="container" type="text" name="description" readonly
                           value="${project.description}"/></td>
                <td>
                    <a class="create"
                       href="${pageContext.request.contextPath}/controller?command=show_update_project_page&id=${project.id}">
                        Изменить проект</a>
                    <button type="submit" class="create">Удалить проект</button>
                </td>
            </tr>
        </form>
    </c:forEach>
</table>
</body>
</html>