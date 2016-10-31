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
						String username = (String) pageContext.getAttribute("pageContextUserName",PageContext.SESSION_SCOPE);
						out.print("Hello "+username+"!!");  
						%>
					</a></li>
				</ul>
			</nav>
			<h4>Items added in  your Cart </h3> 
			<% MySqlDataStoreUtilities mySqlDataStoreUtilities = new MySqlDataStoreUtilities();
			Connection connection = mySqlDataStoreUtilities.getConnection();
			String productid = request.getParameter("productid");
			// String itemname = request.getParameter("name");
			// String quantity = request.getParameter("quantity");
			// String price = request.getParameter("price");
			HashMap<String,ArrayList<String>> fromDBHashMap = new HashMap<String ,ArrayList<String>>();
			// System.out.println(itemname);
			// System.out.println(price);
			// System.out.println(quantity);
			// System.out.println(productid);
			// System.out.println(username);


			mySqlDataStoreUtilities.addToCart(productid,username );
			fromDBHashMap= mySqlDataStoreUtilities.queryFromCart(username);


			for (Map.Entry<String,ArrayList<String>> map1 : fromDBHashMap .entrySet())
		{
			
			out.println("<ul class=styledlist>");

				out.println("<li><form action = \"checkout.jsp\" >UserName is :"+ map1.getValue().get(0) ); 
				out.println("</li>");//retailer
				out.println("<li >Product Name :"+ map1.getValue().get(2));
				out.println("</li>");//phone
				out.println("<li > Price:$"+ map1.getValue().get(3));
				out.println("</li>");//price
				out.println(" Update Quantity:<input type=\"number\"name = \"productid\" value =\""+ map1.getKey()+"\">" +"<input type=\"submit\" value =\"Check Out \"></form>");

				out.println(" <form action = \"removeFromCart.jsp\" ><input type=\"hidden\" method =\"post\" name = \"productid\" value =\""+ map1.getKey()+"\">" +"<input type=\"submit\" value =\"Remove From Cart \"></form>");
				out.println("<br>");
				out.println("</ul class=styledlist>");
				out.println("</ul>");   
				out.println("</ul>");
				System.out.println("key=" + map1.getKey() + ", value=" + map1.getValue());
			}//End of FOr
		


			%>
			</body>
		</html>

			
