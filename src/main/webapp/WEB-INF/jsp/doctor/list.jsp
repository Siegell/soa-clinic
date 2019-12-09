<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<u:html title="Список докторов">
    <c:choose>
        <c:when test="${not empty doctors}">
            <table border="1">
                <tr>
                    <th>Фамилия</th>
                    <th>Имя</th>
                    <th>Отчество</th>
                    <th>Специальность</th>
                    <th>Участок</th>
                    <th>Кабинет</th>
                    <td></td>
                    <td></td>
                </tr>
                <c:forEach var="doctor" items="${doctors}">
                    <c:url var="editUrl" value="/doctor/edit.html">
                        <c:param name="id" value="${doctor.id}"/>
                    </c:url>
                    <c:url var="doctor_scheduleUrl" value="/doctor_schedule/list.html">
                        <c:param name="doctorId" value="${doctor.id}"/>
                    </c:url>
                    <tr>
                        <td>${doctor.lastName}</td>
                        <td>${doctor.firstName}</td>
                        <td>${doctor.middleName}</td>
                        <td>${doctor.specialization}</td>
                        <td>${doctor.district}</td>
                        <td>${doctor.cabinet}</td>
                        <td><a href="${editUrl}">редактировать</a></td>
                        <td><a href="${doctor_scheduleUrl}">расписание доктора</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <p>Список докторов пустой</p>
        </c:otherwise>
    </c:choose>
    <c:url var="editUrl" value="/doctor/edit.html"/>
    <p><a href="${editUrl}">Добавить нового доктора</a></p>

    <c:url var="logout" value="/logout.html"/>
    <p><a href="${logout}">logout</a></p>

    <c:url var="doctorList" value="/user/list.html"/>
    <td><a href="${doctorList}">Управление пользователями</a></td>

</u:html>