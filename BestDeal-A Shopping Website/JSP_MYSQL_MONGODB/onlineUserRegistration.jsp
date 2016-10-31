<%@page import = "java.io.*"
import = "javax.servlet.*"
import = "mypackage.MySqlDataStoreUtilities"
import = "javax.servlet.http.*"	

import="java.sql.*"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Best Deal</title>
</head>
<body>

<%
MySqlDataStoreUtilities mySqlDataStoreUtilities = new MySqlDataStoreUtilities();
Connection connection = mySqlDataStoreUtilities.getConnection();
String u = request.getParameter("username");
		String p = request.getParameter("password");
		String ph = request.getParameter("phone");
		String e = request.getParameter("email");
		String role = request.getParameter("role");

mySqlDataStoreUtilities.insertUser(u,p,ph,e,role);
RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");

rd.include(request,response);

%>
</body>
</html>