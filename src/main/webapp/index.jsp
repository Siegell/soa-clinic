<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>

<u:html title="${Клиника}">
  <c:url var="loginUrl" value="/login.html"/>
  <form action="${loginUrl}" method="post">
    <label for="username">Username:</label><br>
    <input id="username" name="username"><br>
    <label for="password">Password:</label><br>
    <input id="password" name="password"><br>
    <button>Вход в систему</button>
  </form>
  <c:url var="doctorList" value="/doctor/list.html"/>
  <td><a href="${doctorList}">Продолжить без входа</a></td>
</u:html>