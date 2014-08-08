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
    <h1>Welcome to Add Manager</h1>
    <h2>Newspapers</h2>
    <table>
      <tr>
        <th>Name</th>
        <th>State</th>
      </tr>
      <c:forEach items="${newspapers}" var="newspaper">
      <tr>
        <td>${newspaper.name}</td>
        <td>${newspaper.state}</td>
      </tr>
      </c:forEach>
     </table>

     <a href="${contextPath}/addNewspaper">Add Newspaper</a><br>

     <h2>Advertisements</h2>
          <table>
             <tr>
                <th>Name</th>
                <th>Text</th>
                <th>Associated Newspapers</th>
             </tr>
             <c:forEach items="${advertisements}" var="advertisement">
             <tr>
                 <td>${advertisement.name}</td>
                 <td>${advertisement.text}</td>
                 <td>${advertisement.newspapers}</td>
                 <td><a href="${contextPath}/addToNewspaper/${advertisement.id}">Edit</a></td>
                 </tr>
             </c:forEach>
          </table>

    <a href="${contextPath}/addAdvertisement">Add Advertisement</a><br>
  </body>
</html>