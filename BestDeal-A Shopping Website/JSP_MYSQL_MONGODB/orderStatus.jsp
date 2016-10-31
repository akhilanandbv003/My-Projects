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
			String username = u;
			HashMap<String,ArrayList<String>> fromDBHashMap=mySqlDataStoreUtilities.getOrderDetails(username);

			

			for (Map.Entry<String,ArrayList<String>> map1 : fromDBHashMap .entrySet())
				{
			System.out.println("Shopping Cart Home for ");

			out.println("Order details");

			out.println("<ul class=styledlist>");			
			out.println("<li>Logged in as :"+ map1.getValue().get(0));
			out.println("</li>");
			out.println("<li>Your Orderid is :#"+ map1.getValue().get(1));
				out.println("</li>");//Orderid
				
				out.println("<li>Item Purchased :"+ map1.getValue().get(2));
				out.println("</li>");
				out.println("<li> Total Amount paid :$"+ map1.getValue().get(3));
				out.println("</li>");//price
				out.println("<li><b> Status of the Order is  :"+ map1.getValue().get(4)+"</b>");
				out.println("</li>");
				out.println("<br>");
				out.println("Click on this to cancel this order <form action=\" cancelOrder.jsp\" method = \"post\">");
				out.println("<input type = \"hidden\" name = \"orderid\" value =\""+ map1.getKey() + "\">" + "<button>Cancel this Order?</button></form>");
				out.println("<br>");
				out.println("</ul class=styledlist>");
				out.println("</ul>");   
				out.println("</ul>");
			}
			%>

		</body>
		</html>