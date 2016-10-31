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
String ProductName = request.getParameter("ProductName");
 			String ProductCategory = request.getParameter("ProductCategory");
 			String ProductPrice = request.getParameter("ProductPrice");
 			String RetailerName = request.getParameter("RetailerName");
 			String RetailerZip = request.getParameter("RetailerZip");
 			String RetailerCity = request.getParameter("RetailerCity");
 			String RetailerState = request.getParameter("RetailerState");
 			String ProductOnSale = request.getParameter("ProductOnSale");
 			String ManufacturerName = request.getParameter("ManufacturerName");
 			String ManufacturerRebate = request.getParameter("ManufacturerRebate");
 			String UserID = request.getParameter("UserID");
 			String UserAge = request.getParameter("UserAge");
 			String UserGender = request.getParameter("UserGender");
 			String UserOccupation = request.getParameter("UserOccupation");
 			String ReviewRating = request.getParameter("ReviewRating");
 			String Date = request.getParameter("Date");
 			String ReviewText = request.getParameter("ReviewText");
MongoDBDataStoreUtilities mongoDBDataStoreUtilities = new MongoDBDataStoreUtilities();
Map<String, String[]> requestMap = request.getParameterMap();//From the request object take all the data
mongoDBDataStoreUtilities.insertReviews(requestMap, ProductName, ProductCategory,
 		 ProductPrice , RetailerName, RetailerZip, RetailerCity, RetailerState, ProductOnSale, ManufacturerName, ManufacturerRebate,
UserID , UserAge, UserGender, UserOccupation, Date, ReviewText);

	%>

	<h4>

	Reviews submitted to the MongoDB!!

		</body>
		</html>