<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>Sample Application</title>
  </head>
  <body>
  <c:set var="context" value="${pageContext.request.contextPath}" />
    <h1>Create an Advertisement</h1>
        <form method="post" action="${context}/advertisement">
            Name: <input type="text" name="name"><br>
            Text: <textarea cols="40" rows="5" name="text">
            </textarea><br>
        <input type="submit" value="Submit">
        </form>
  </body>
</html>