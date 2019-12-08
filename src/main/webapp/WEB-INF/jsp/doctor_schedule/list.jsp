<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<u:html title="Расписание докторов">
    <c:choose>
        <c:when test="${not empty doctorSchedules}">
            <table border="1">
                <tr>
                    <th>Дата</th>
                    <th>Начало работы</th>
                    <th>Конец работы</th>
                    <th>Количество талонов на приём</th>
                    <td></td>
                    <td></td>
                </tr>
                <c:forEach var="doctorSchedule" items="${doctorSchedules}">
                    <c:url var="editUrl" value="/doctor_schedule/edit.html">
                        <c:param name="id" value="${doctorSchedule.id}"/>
                    </c:url>
                    <c:url var="appointmentUrl" value="/appointment/list.html">
                        <c:param name="doctorScheduleId" value="${doctorSchedule.id}"/>
                    </c:url>
                    <tr>
                        <td>${doctorSchedule.date}</td>
                        <td>${doctorSchedule.startWork}</td>
                        <td>${doctorSchedule.endWork}</td>
                        <td>${doctorSchedule.maxAppointmentCount}</td>
                        <td><a href="${editUrl}">редактировать</a></td>
                        <td><a href="${appointmentUrl}">талоны</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <p>Расписание доктора пусто</p>
        </c:otherwise>
    </c:choose>
    <c:url var="editUrl" value="/doctor_schedule/edit.html"/>
    <p><a href="${editUrl}">Добавить расписание</a></p>
</u:html>