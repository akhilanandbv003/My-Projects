
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import = "java.io.*"
import="java.sql.*"
import="java.util.*"
import = "mypackage.MySqlDataStoreUtilities"
import="javax.servlet.*"
import="com.mongodb.*"%>

<html>

<head>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Best Deal </title>
		<link rel="stylesheet" href="styles.css" type="text/css" />
	</head>
	<html>
	<head>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
			<title>Best Deal </title>
			<link rel="stylesheet" href="styles.css" type="text/css" />
		</head>

		<body>
			<div id="container">
				<header>
					<h1>
						<a href="/">Best  <span>Deal</span></a>
					</h1>
					<h2>The Best Deals are here!!!!</h2>

					<form class="role-form" action="http://localhost:8080/csj/formServlet" method="post">
						<button type = "submit" name = "role" class="role" value = "SalesMan"> SalesMan</button>
						<button type = "submit" name = "role" class="role" value = "StoreManager">Store Manager</button>
						<button type = "submit" name = "role" class="role" value = "Customer">Customer</button>	
					</form>

				</header>

				<nav>
					<ul>
						<li class="start selected"><a href="index.html">Home</a></li>
						<li class=""><a href="Error.html">SmartPhones</a></li>
						<li class=""><a href="Error.html">Laptop</a></li>
						<li class=""><a href="Error.html">Tablets</a></li>
						<li class=""><a href="Error.html">TV</a></li>
						<li class="end"><a href="#">
							<%
							String u = (String) pageContext.getAttribute("pageContextUserName",PageContext.SESSION_SCOPE);
							out.print("Hello "+u+"!!");  
							%>
						</a></li>
					</ul>
				</nav>
				<body>
					<% MySqlDataStoreUtilities mySqlDataStoreUtilities = new MySqlDataStoreUtilities();
					Connection connection = mySqlDataStoreUtilities.getConnection();
					String username =  request.getParameter("username");		 
					String orderid =  request.getParameter("orderid");		
		String itemname = request.getParameter("itemname");//Phone name
		String price = request.getParameter("price");
		System.out.println("Price is "+price);
		String quantity = request.getParameter("quantity");
		String orderstatus = request.getParameter("orderstatus");
		String datepurchased = request.getParameter("datepurchased");
		mySqlDataStoreUtilities.updateOrders(username, itemname,quantity,price,datepurchased,orderid,orderstatus);

		out.print("<h1>You updated the order# "+orderid+" and the update was Successful</h1> ");
		%>

	</body>
	</html>