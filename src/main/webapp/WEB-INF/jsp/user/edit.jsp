<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<c:choose>
    <c:when test="${not empty user}">
        <c:set var="title" value="Редактирование пользователя"/>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Добавление нового пользователя"/>
        <jsp:useBean id="user" class="by.siegell.soa.clinic.domain.User"/>
    </c:otherwise>
</c:choose>

<u:html title="${title}">
    <c:url var="saveUrl" value="/user/save.html"/>
    <form action="${saveUrl}" method="post">
        <c:if test="${not empty user.id}">
            <input type="hidden" name="id" value="${user.id}">
        </c:if>
        <label for="username">Имя пользователя:</label><br>
        <input id="username" name="username" value="${user.username}"><br>
        <label for="password">Пароль:</label><br>
        <input id="password" name="password" value="${user.password}"><br>
        <label for="roles">Роли(без пробелов через |, пример: admin|nurse):</label><br>
        <input id="roles" name="roles" value="${user.roles}"><br>
        <button>Сохранить</button>
    </form>
    <c:if test="${not empty user.id}">
        <c:url var="deleteUrl" value="/user/delete.html"/>
        <form action="${deleteUrl}" method="post">
            <input type="hidden" name="id" value="${user.id}">
            <button>Удалить</button>
        </form>
    </c:if>
    <c:url var="logout" value="/logout.html"/>
    <p><a href="${logout}">logout</a></p>

</u:html>