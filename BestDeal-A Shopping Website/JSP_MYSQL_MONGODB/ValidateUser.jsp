<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

    


<%--
<jsp:useBean id="user" class ="mypackage.User" scope="request"> 
<jsp:setProperty property="*" name ="user"/>

</jsp:useBean>


Username is<jsp:getProperty property="username" name="user"/>
password entered in form   is <jsp:getProperty property="password" name="user"/>
--%>

<%
MySqlDataStoreUtilities mySqlDataStoreUtilities = new MySqlDataStoreUtilities();
Connection connection = mySqlDataStoreUtilities.getConnection();
ResultSet resultSet = mySqlDataStoreUtilities.getLoginDetails();
String username = request.getParameter("username");
String password = request.getParameter("password");
String role = request.getParameter("role");

pageContext.setAttribute("pageContextUserName",username,PageContext.SESSION_SCOPE);

System.out.println("username entered in form  is " + username);
System.out.println("password entered in form   is " + password);
while (resultSet.next())
    {
        String string7 = resultSet.getString(1);
        String string8 = resultSet.getString(2);
        System.out.println("2nd item is #####" + string8);
        System.out.println("1st item is #####" + string7);
        if (!username.trim().equals(string7.trim()) || !password.trim().equals(string8.trim())) continue;


        if (role.trim().equalsIgnoreCase("customer"))
            {   

                RequestDispatcher dispatcher =request.getRequestDispatcher("UserHome.jsp"); 
                dispatcher.forward(request, response);  
            }
        else if(role.trim().equalsIgnoreCase("salesman"))
            {
                RequestDispatcher dispatcher = request.getRequestDispatcher("salesmanHome.jsp");                    
                dispatcher.forward(request, response);
            }
        else if (role.trim().equalsIgnoreCase("storemanager"))
            {
                RequestDispatcher dispatcher = request.getRequestDispatcher("storemanagerHome.jsp");                    
                dispatcher.forward(request, response);
            }
        break;
    }


%>
</body>
</html>