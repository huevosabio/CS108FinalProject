<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html5/strict.dtd">
<html>
<% String name = (String) getServletContext().getAttribute("user");%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><% out.println(name + " already in use"); %> </title>
</head>
<body>
<div style= "font-size: large"><b><%out.println(name + " in use.");%></b></div>
<% out.println(name + " is taken; please enter a different name."); %> 

<form action="AcctManagerServlet" method="post">
<input type="hidden" name="Action" value ="Create">
Username: <input type="text" name="User"><br>
Password: <input type="text" name="Pass">
<input type="submit" value="Create Account">
</form>
</body>
</html>