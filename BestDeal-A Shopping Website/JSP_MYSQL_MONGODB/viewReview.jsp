<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import = "java.io.*"
import="java.sql.*"
import="java.util.*"
import = "mypackage.MongoDBDataStoreUtilities"
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

				<form class="role-form" action="shoppingCartHome.jsp" method="post">
					<button type = "submit" name = "role" class="role" value = "shoppingCartHome"> Shopping Cart </button>
					
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
<% 
String ProductName = request.getParameter("productname");
 			
MongoDBDataStoreUtilities mongoDBDataStoreUtilities = new MongoDBDataStoreUtilities();

Map<String, String[]> requestMap = request.getParameterMap();//From the request object take all the data
DBCursor cursor = mongoDBDataStoreUtilities.viewReviews( ProductName);
System.out.println("CUrsor from MongoDB is "+cursor);
if (!cursor.hasNext()) {
				out.println("No reviews for this product");
						
					}		
			else{


			while(cursor.hasNext())
			{	
			System.out.println("Inside where Cursor")			;
				DBObject row = cursor.next();

				out.println("<table border=\"1\">");

				
				
				out.println("<tr>");
				out.println("<td>");
				out.println("<b> RetailerName "+row.get("ProductName"));
				out.println("</td>");
				out.println("<b>ProductCategory "+ row.get("ProductCategory"));
				out.println("<td>");
				out.println("<td>");
				out.println("<b>ProductPrice  "+ row.get("ProductPrice"));
				out.println("</td>");
				out.println("<td>");
				out.println("<b>RetailerZip "+row.get("RetailerZip"));
				out.println("</td>");
				out.println("<td>");
				out.println("<b>RetailerCity "+row.get("RetailerCity"));
				out.println("</td>");
				out.println("<td>");
				out.println("<b>RetailerState "+row.get("RetailerState"));
				out.println("</td>");
				out.println("<td>");
				out.println("<b>ProductOnSale "+row.get("ProductOnSale"));
				out.println("</td>");
				out.println("<td>");
				out.println("<b>ManufacturerName "+row.get("ManufacturerName"));
				out.println("</td>");
				out.println("<td>");
				out.println("<b>ManufacturerRebate "+row.get("ManufacturerRebate"));
				out.println("</td>");
				out.println("<td>");
				out.println("<b>UserID "+row.get("UserID"));
				out.println("</td>");
				out.println("<td>");
				out.println("<b>UserAge "+row.get("UserAge"));
				out.println("</td>");
				out.println("<td>");
				out.println("<b>UserGender "+row.get("UserGender"));
				out.println("</td>");
				out.println("<td>");
				out.println("<b>UserOccupation "+row.get("UserOccupation"));
				out.println("</td>");
				out.println("<tr>");
				out.println("<td>");
				out.println("<b> ReviewRating "+row.get("ReviewRating"));
				out.println("</td>");
				out.println("<td>");
				out.println("<b>Date "+row.get("Date"));
				out.println("</td>");
				out.println("<td>");
				out.println("<b>ReviewText "+row.get("ReviewText"));
				out.println("</td>");


				



				


			}
			}

out.println("</tr>");
				out.println("</table>");


	%>

	
		</body>
		</html>