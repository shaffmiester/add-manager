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
      <h1>Add Advertisement to Newspaper</h1>
          Advertisement Name: ${advertisement.name} <br>
          Advertisement Text: ${advertisement.text} <br>

          Select newspaper(s) this add should be associated with: <br><br>

          <form method="post" action="${contextPath}/addToNewspaper/${advertisement.id}">
            <select multiple name="newspaperIds">
              <c:forEach items="${newspapers}" var="newspaper">
                <option value="${newspaper.id}">${newspaper.name}</option>
              </c:forEach>
            </select><br>
            <input type="submit" value="Submit">
          </form>
    </body>
</html>