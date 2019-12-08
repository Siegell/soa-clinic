<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<c:choose>
    <c:when test="${not empty doctorSchedule}">
        <c:set var="title" value="Редактирование расписания"/>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Добавление нового расписания"/>
        <jsp:useBean id="doctorSchedule" class="by.siegell.soa.clinic.domain.DoctorSchedule"/>
    </c:otherwise>
</c:choose>

<u:html title="${title}">
    <c:url var="saveUrl" value="/doctor_schedule/save.html"/>
    <form action="${saveUrl}" method="post">
        <c:if test="${not empty doctorSchedule.id}">
            <input type="hidden" name="id" value="${doctorSchedule.id}">
            <input type="hidden" name="createdAt" value="${doctorSchedule.createdAt}">
            <input type="hidden" name="updatedAt" value="${doctorSchedule.updatedAt}">
        </c:if>
        <label for="date">Дата:</label><br>
        <input id="date" name="date" value="${doctorSchedule.date}"><br>
        <label for="startWork">Начало работы:</label><br>
        <input id="startWork" name="startWork" value="${doctorSchedule.startWork}"><br>
        <label for="endWork">Конец работы:</label><br>
        <input id="endWork" name="endWork" value="${doctorSchedule.endWork}"><br>
        <label for="maxAppointmentCount">Количество талонов на приём:</label><br>
        <input id="maxAppointmentCount" name="maxAppointmentCount" value="${doctorSchedule.maxAppointmentCount}"><br>
        <input type="hidden" name="doctorId" value="${doctorSchedule.doctorId}">
        <button>Сохранить</button>
    </form>
    <c:if test="${not empty doctorSchedule.id}">
        <c:url var="deleteUrl" value="/doctor_schedule/delete.html"/>
        <form action="${deleteUrl}" method="post">
            <input type="hidden" name="id" value="${doctorSchedule.id}">
            <button>Удалить</button>
        </form>
    </c:if>
</u:html>