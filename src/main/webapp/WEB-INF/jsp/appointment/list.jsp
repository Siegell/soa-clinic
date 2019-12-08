<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<u:html title="Список талонов">
    <c:choose>
        <c:when test="${not empty appointments}">
            <table border="1">
                <tr>
                    <th>Начало приёма</th>
                    <th>Конец приёма</th>
                    <th>Фамилия</th>
                    <th>Имя</th>
                    <th>Отчество</th>
                    <td></td>
                </tr>
                <c:forEach var="appointment" items="${appointments}">
                    <c:url var="editUrl" value="/appointment/edit.html">
                        <c:param name="id" value="${appointment.id}"/>
                    </c:url>
                    <tr>
                        <td>${appointment.startTime}</td>
                        <td>${appointment.endTime}</td>
                        <td>${appointment.lastName}</td>
                        <td>${appointment.firstName}</td>
                        <td>${appointment.middleName}</td>
                        <td><a href="${editUrl}">редактировать</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <p>Список талонов пустой</p>
        </c:otherwise>
    </c:choose>
    <c:url var="editUrl" value="/appointment/edit.html">
        <c:param name="doctorScheduleId" value="${doctorScheduleId}"/>
    </c:url>
    <p><a href="${editUrl}">Добавить новый талон</a></p>
</u:html>