<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<u:html title="Appointments list">
    <c:choose>
        <c:when test="${not empty appointments}">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Start time</th>
                        <th>End time</th>
                        <th>First name</th>
                        <th>Last name</th>
                        <th>Middle name</th>
                        <td></td>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="appointment" items="${appointments}">
                    <c:url var="editUrl" value="/appointment/edit.html">
                        <c:param name="id" value="${appointment.id}"/>
                    </c:url>
                    <tr>
                        <td>${appointment.startTime}</td>
                        <td>${appointment.endTime}</td>
                        <td>${appointment.firstName}</td>
                        <td>${appointment.lastName}</td>
                        <td>${appointment.middleName}</td>
                        <td><a href="${editUrl}">edit</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p>Appointments list is empty</p>
        </c:otherwise>
    </c:choose>

    <c:url var="editUrl" value="/appointment/edit.html">
        <c:param name="doctorScheduleId" value="${doctorScheduleId}"/>
    </c:url>
    <p><a href="${editUrl}">Add appointment</a></p>

    <c:url var="logout" value="/logout.html"/>
    <div class="mt-2">
        <a class="btn btn-primary" href="${logout}">Logout</a>
    </div>

</u:html>