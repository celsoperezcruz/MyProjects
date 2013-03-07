<%-- 
    Document   : buildschema
    Created on : 6/03/2013, 09:08:04 AM
    Author     : Adm
--%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.lang.String" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript">
            function go(url)
            {
                window.location = url;
            }
            function deleteContact(url)
            {
                var isOK = confirm("Seguro que decea elminiar el registro?");
                if (isOK)
                {
                    go(url);
                }
            }
        </script>
    </head>

    <body>
        <table>
            <tr>
                <td>
                    <form:form action="save.htm" commandName="newUsers">
                        <table>                            
                            <tr>
                                <td>ID:</td>
                                <td><form:input path="id" /></td>
                            </tr>
                            <tr>
                                <td>First Name:</td>
                                <td><form:input path="name" /></td>
                            </tr>
                            <tr>
                                <td>Age:</td>
                                <td><form:input path="age" /></td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <input type="submit" value="Save Changes" />
                                </td>
                            </tr>
                        </table>
                    </form:form>

                </td>
            </tr>
            <tr>
            <table>
                <c:forEach items="${listUsers}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td><c:out value="${user.name}" /></td>
                        <td>${user.age}</td>
                        <td><input onclick="javascript:deleteContact('delete.htm?id=${user.id}');" type="button" value="Delete"></td>
                    </tr>
                </c:forEach>
            </table>
        </tr>
    </table>
</body>
</html>
