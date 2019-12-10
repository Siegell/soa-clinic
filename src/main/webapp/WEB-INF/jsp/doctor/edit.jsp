<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<c:choose>
    <c:when test="${not empty doctor.id}">
        <c:set var="title" value="Editing doctor ${doctor.firstName} ${doctor.lastName}"/>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Adding new doctor"/>
        <jsp:useBean id="doctor" class="by.siegell.soa.clinic.domain.Doctor"/>
    </c:otherwise>
</c:choose>

<u:html title="${title}">
    <c:url var="saveUrl" value="/doctor/save.html"/>
    <form action="${saveUrl}" method="post">
        <c:if test="${not empty doctor.id}">
            <input type="hidden" name="id" value="${doctor.id}">
            <input type="hidden" name="createdAt" value="${doctor.createdAt}">
            <input type="hidden" name="updatedAt" value="${doctor.updatedAt}">
        </c:if>
        <div class="form-group">
            <label for="first-name">First name:</label>
            <input id="first-name" name="firstName" value="${doctor.firstName}" class="form-control">
        </div>
        <div class="form-group">
            <label for="last-name">Last name:</label>
            <input id="last-name" name="lastName" value="${doctor.lastName}" class="form-control">
        </div>
        <div class="form-group">
            <label for="middle-name">Middle name:</label>
            <input id="middle-name" name="middleName" value="${doctor.middleName}" class="form-control">
        </div>
        <div class="form-group">
            <label for="specialization">Specialty:</label>
            <input id="specialization" name="specialization" value="${doctor.specialization}" class="form-control">
        </div>
        <div class="form-group">
            <label for="district">Plot:</label>
            <input id="district" name="district" value="${doctor.district}" class="form-control">
        </div>
        <div class="form-group">
            <label for="cabinet">Cabinet:</label>
            <input id="cabinet" name="cabinet" value="${doctor.cabinet}" class="form-control">
        </div>
        <button type="submit" class="btn btn-primary">Save</button>
    </form>

    <c:if test="${not empty doctor.id}">
        <c:url var="deleteUrl" value="/doctor/delete.html"/>
        <form action="${deleteUrl}" method="post" class="mt-2">
            <input type="hidden" name="id" value="${doctor.id}">
            <button type="submit" class="btn btn-primary">Delete</button>
        </form>
    </c:if>

    <c:url var="logout" value="/logout.html"/>
    <div class="mt-2">
        <a class="btn btn-primary" href="${logout}">Logout</a>
    </div>
</u:html>