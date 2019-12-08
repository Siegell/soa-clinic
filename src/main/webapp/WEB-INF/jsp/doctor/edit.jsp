<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<c:choose>
    <c:when test="${not empty doctor}">
        <c:set var="title" value="Редактирование доктора ${doctor.firstName} ${doctor.lastName}"/>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Добавление нового доктора"/>
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
        <label for="first-name">Имя:</label><br>
        <input id="first-name" name="firstName" value="${doctor.firstName}"><br>
        <label for="last-name">Фамилия:</label><br>
        <input id="last-name" name="lastName" value="${doctor.lastName}"><br>
        <label for="last-name">Отчество:</label><br>
        <input id="middle-name" name="middleName" value="${doctor.middleName}"><br>
        <label for="last-name">Специальность:</label><br>
        <input id="specialization" name="specialization" value="${doctor.specialization}"><br>
        <label for="last-name">Участок:</label><br>
        <input id="district" name="district" value="${doctor.district}"><br>
        <label for="last-name">Кабинет:</label><br>
        <input id="cabinet" name="cabinet" value="${doctor.cabinet}"><br>
        <button>Сохранить</button>
    </form>
    <c:if test="${not empty doctor.id}">
        <c:url var="deleteUrl" value="/doctor/delete.html"/>
        <form action="${deleteUrl}" method="post">
            <input type="hidden" name="id" value="${doctor.id}">
            <button>Удалить</button>
        </form>
    </c:if>
</u:html>