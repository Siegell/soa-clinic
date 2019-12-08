<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<c:choose>
    <c:when test="${not empty appointment}">
        <c:set var="title" value="Редактирование талона"/>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Добавление нового талона"/>
        <jsp:useBean id="appointment" class="by.siegell.soa.clinic.domain.Appointment"/>
    </c:otherwise>
</c:choose>

<u:html title="${title}">
    <c:url var="saveUrl" value="/appointment/save.html"/>
    <form action="${saveUrl}" method="post">
        <c:if test="${not empty appointment.id}">
            <input type="hidden" name="id" value="${appointment.id}">
            <input type="hidden" name="createdAt" value="${appointment.createdAt}">
            <input type="hidden" name="updatedAt" value="${appointment.updatedAt}">
        </c:if>
        <label for="startTime">Начало приёма:</label><br>
        <input id="startTime" name="startTime" value="${appointment.startTime}"><br>
        <label for="endTime">Конец приёма:</label><br>
        <input id="endTime" name="endTime" value="${appointment.endTime}"><br>
        <label for="first-name">Имя:</label><br>
        <input id="first-name" name="firstName" value="${appointment.firstName}"><br>
        <label for="last-name">Фамилия:</label><br>
        <input id="last-name" name="lastName" value="${appointment.lastName}"><br>
        <label for="last-name">Отчество:</label><br>
        <input id="middle-name" name="middleName" value="${appointment.middleName}"><br>
        <input type="hidden" name="doctorScheduleId" value="${appointment.doctorScheduleId}">
        <button>Сохранить</button>
    </form>
    <c:if test="${not empty appointment.id}">
        <c:url var="deleteUrl" value="/appointment/delete.html"/>
        <form action="${deleteUrl}" method="post">
            <input type="hidden" name="id" value="${appointment.id}">
            <button>Удалить</button>
        </form>
    </c:if>
</u:html>