<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<u:html title="Users list">
    <c:choose>
        <c:when test="${not empty users}">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Password</th>
                        <th>Roles</th>
                        <td></td>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${users}">
                    <c:url var="editUrl" value="/user/edit.html">
                        <c:param name="id" value="${user.id}"/>
                    </c:url>
                    <tr>
                        <td>${user.username}</td>
                        <td>${user.password}</td>
                        <td>${user.roles}</td>

                        <td><a href="${editUrl}">edit</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p>Users list is empty</p>
        </c:otherwise>
    </c:choose>

    <c:url var="editUrl" value="/user/edit.html"/>
    <p><a href="${editUrl}">Add user</a></p>

    <c:url var="logout" value="/logout.html"/>
    <div class="mt-2">
        <a class="btn btn-primary" href="${logout}">Logout</a>
    </div>

</u:html>