<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<u:html title="Список пользователей">
    <c:choose>
        <c:when test="${not empty users}">
            <table border="1">
                <tr>
                    <th>username</th>
                    <th>password</th>
                    <th>roles</th>
                    <td></td>
                </tr>
                <c:forEach var="user" items="${users}">
                    <c:url var="editUrl" value="/user/edit.html">
                        <c:param name="id" value="${user.id}"/>
                    </c:url>
                    <tr>
                        <td>${user.username}</td>
                        <td>${user.password}</td>
                        <td>${user.roles}</td>

                        <td><a href="${editUrl}">редактировать</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <p>Список пользователей пустой</p>
        </c:otherwise>
    </c:choose>
    <c:url var="editUrl" value="/user/edit.html"/>
    <p><a href="${editUrl}">Добавить нового пользователя</a></p>
    <c:url var="logout" value="/logout.html"/>
    <p><a href="${logout}">logout</a></p>

</u:html>