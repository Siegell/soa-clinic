<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<c:choose>
    <c:when test="${not empty doctorSchedule.id}">
        <c:set var="title" value="Editing schedule"/>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Adding new schedule"/>
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
        <div class="form-group">
            <label for="date">Date:</label>
            <input id="date" name="date" type="date" value="${doctorSchedule.date}" class="form-control">
        </div>
        <div class="form-group">
            <label for="startWork">Start work:</label>
            <input id="startWork" name="startWork" type="time" value="${doctorSchedule.startWork}" class="form-control">
        </div>
        <div class="form-group">
            <label for="endWork">End work:</label>
            <input id="endWork" name="endWork" type="time" value="${doctorSchedule.endWork}" class="form-control">
        </div>
        <div class="form-group">
            <label for="maxAppointmentCount">Appointments amount:</label>
            <input id="maxAppointmentCount" name="maxAppointmentCount" value="${doctorSchedule.maxAppointmentCount}" class="form-control">
        </div>
        <input type="hidden" name="doctorId" value="${doctorSchedule.doctorId}">
        <button type="submit" class="btn btn-primary">Save</button>
    </form>
    <c:if test="${not empty doctorSchedule.id}">
        <c:url var="deleteUrl" value="/doctor_schedule/delete.html">
            <c:param name="doctorId" value="${doctorSchedule.doctorId}"/>
        </c:url>
        <form action="${deleteUrl}" method="post" class="mt-2">
            <input type="hidden" name="id" value="${doctorSchedule.id}">
            <button type="submit" class="btn btn-primary">Delete</button>
        </form>
    </c:if>
    <c:url var="logout" value="/logout.html"/>
    <div class="mt-2">
        <a class="btn btn-primary" href="${logout}">Logout</a>
    </div>

</u:html>