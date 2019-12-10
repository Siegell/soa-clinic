<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<u:html title="Doctor schedule">
    <c:choose>
        <c:when test="${not empty doctorSchedules}">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Date</th>
                        <th>Start work</th>
                        <th>End work</th>
                        <th>Appointments amount</th>
                        <td></td>
                        <td></td>
                    </tr>
                </thead>
                <tbody>
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
                        <td><a href="${editUrl}">edit</a></td>
                        <td><a href="${appointmentUrl}">coupons</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p>Doctor schedule is empty</p>
        </c:otherwise>
    </c:choose>

    <c:url var="editUrl" value="/doctor_schedule/edit.html">
        <c:param name="doctorId" value="${doctorId}"/>
    </c:url>
    <p><a href="${editUrl}">Add schedule</a></p>

    <c:url var="logout" value="/logout.html"/>
    <div class="mt-2">
        <a class="btn btn-primary" href="${logout}">Logout</a>
    </div>

</u:html>