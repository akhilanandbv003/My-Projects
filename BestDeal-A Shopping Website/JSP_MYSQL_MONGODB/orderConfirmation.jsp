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
			<% MySqlDataStoreUtilities mySqlDataStoreUtilities = new MySqlDataStoreUtilities();
			Connection connection = mySqlDataStoreUtilities.getConnection();
			java.util.Date date1 = new java.util.Date();
			Random t = new Random();

			String itemname = request.getParameter("itemname");
			String quantity = request.getParameter("quantity");
			String price = request.getParameter("price");

			String retailer = request.getParameter("retailer");
			String productname = request.getParameter("name");

			
			String datepurchased = date1.toString();			
			int r =t.nextInt(100);			
			String orderid = Integer.toString(r);
			String orderstatus = "order placed "; 


			mySqlDataStoreUtilities.insertOrders(u, itemname, quantity ,price,  datepurchased , orderid,orderstatus);

			out.println("<h3> Hello "+u);
			out.println("<h3> Your Order has been successfully Placed ");
			out.println("<h3> Your Order # is "+orderid);
			out.println("<h3> You can use this ordernumber for further queries");
			int q = Integer.parseInt(quantity);
			int p = Integer.parseInt(price);
			int tot=p*q;
			out.println("<h3> Total bill is "+tot);


			%>
		</body>
		</html>