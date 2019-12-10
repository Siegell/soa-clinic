<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<c:choose>
    <c:when test="${not empty user}">
        <c:set var="title" value="Editing user"/>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Adding new user"/>
        <jsp:useBean id="user" class="by.siegell.soa.clinic.domain.User"/>
    </c:otherwise>
</c:choose>

<u:html title="${title}">
    <c:url var="saveUrl" value="/user/save.html"/>
    <form action="${saveUrl}" method="post">
        <c:if test="${not empty user.id}">
            <input type="hidden" name="id" value="${user.id}">
        </c:if>
        <div class="form-group">
            <label for="username">Username:</label>
            <input id="username" name="username" value="${user.username}" class="form-control">
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input id="password" name="password" value="${user.password}" class="form-control">
        </div>
        <div class="form-group">
            <label for="roles">Roles (example: admin|nurse):</label>
            <input id="roles" name="roles" value="${user.roles}" class="form-control">
        </div>
        <button type="submit" class="btn btn-primary">Save</button>
    </form>
    <c:if test="${not empty user.id}">
        <c:url var="deleteUrl" value="/user/delete.html"/>
        <form action="${deleteUrl}" method="post">
            <input type="hidden" name="id" value="${user.id}">
            <button type="submit" class="btn btn-primary">Delete</button>
        </form>
    </c:if>

    <c:url var="logout" value="/logout.html"/>
    <div class="mt-2">
        <a class="btn btn-primary" href="${logout}">Logout</a>
    </div>

</u:html>