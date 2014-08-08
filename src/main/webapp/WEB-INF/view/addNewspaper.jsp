<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>Sample Application</title>
  </head>
  <h1>Add a Newspaper</h1>
  <body>
  <c:set var="context" value="${pageContext.request.contextPath}" />
    <h1>Create an Advertisement</h1>
        <form method="post" action="${context}/newspaper">
            Name: <input type="text" name="name"><br>
            State: <input type="text" name="state"><br>
        <input type="submit" value="Submit">
        </form>
  </body>
</html>