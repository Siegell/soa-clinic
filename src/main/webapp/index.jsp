<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>

<u:html title="Clinic">
  <c:url var="loginUrl" value="/login.html"/>
  <form action="${loginUrl}" method="post">
    <div class="form-group">
      <label for="username">Username:</label>
      <input id="username" name="username" class="form-control">
    </div>
    <div class="form-group">
      <label for="password">Password:</label>
      <input id="password" type="password" name="password" class="form-control">
    </div>
    <button type="submit" class="btn btn-primary">Login</button>
  </form>
  <c:url var="doctorList" value="/doctor/list.html"/>
  <a class="mt-2" href="${doctorList}">Continue without login</a>
</u:html>