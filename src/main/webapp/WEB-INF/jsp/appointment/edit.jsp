<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<c:choose>
    <c:when test="${not empty appointment.id}">
        <c:set var="title" value="Editing appointment"/>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Adding new appointment"/>
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
        <div class="form-group">
            <label for="startTime">Start time:</label>
            <input id="startTime" name="startTime" type="time" value="${appointment.startTime}" class="form-control">
        </div>
        <div class="form-group">
            <label for="endTime">End time:</label>
            <input id="endTime" name="endTime" type="time" value="${appointment.endTime}" class="form-control">
        </div>
        <div class="form-group">
            <label for="first-name">Last name:</label>
            <input id="first-name" name="firstName" value="${appointment.firstName}" class="form-control">
        </div>
        <div class="form-group">
            <label for="last-name">First name:</label>
            <input id="last-name" name="lastName" value="${appointment.lastName}" class="form-control">
        </div>
        <div class="form-group">
            <label for="middle-name">Middle name:</label>
            <input id="middle-name" name="middleName" value="${appointment.middleName}" class="form-control">
        </div>
        <input type="hidden" name="doctorScheduleId" value="${appointment.doctorScheduleId}">
        <button type="submit" class="btn btn-primary">Save</button>
    </form>

    <c:if test="${not empty appointment.id}">
        <c:url var="deleteUrl" value="/appointment/delete.html">
            <c:param name="doctorScheduleId" value="${appointment.doctorScheduleId}"/>
        </c:url>
        <form action="${deleteUrl}" method="post" class="mt-2">
            <input type="hidden" name="id" value="${appointment.id}">
            <button type="submit" class="btn btn-primary">Delete</button>
        </form>
    </c:if>

    <c:url var="logout" value="/logout.html"/>
    <div class="mt-2">
        <a class="btn btn-primary" href="${logout}">Logout</a>
    </div>
</u:html>