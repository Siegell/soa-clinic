<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<u:html title="Doctors list">
    <c:choose>
        <c:when test="${not empty doctors}">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>First name</th>
                        <th>Last name</th>
                        <th>Middle name</th>
                        <th>Specialty</th>
                        <th>Plot</th>
                        <th>Cabinet</th>
                        <td></td>
                        <td></td>
                    </tr>
                </thead>
                <tbody>
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
                        <td><a href="${editUrl}">edit</a></td>
                        <td><a href="${doctor_scheduleUrl}">schedule</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p>Doctors list is empty</p>
        </c:otherwise>
    </c:choose>
    <c:url var="editUrl" value="/doctor/edit.html"/>
    <p><a href="${editUrl}">Add doctor</a></p>

    <c:url var="doctorList" value="/user/list.html"/>
    <a href="${doctorList}">Manage users</a>

    <c:url var="logout" value="/logout.html"/>
    <div class="mt-2">
        <a class="btn btn-primary" href="${logout}">Logout</a>
    </div>

</u:html>