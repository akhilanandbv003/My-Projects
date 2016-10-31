
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
						String u = (String) pageContext.getAttribute("pageContextUserName",PageContext.SESSION_SCOPE);
						out.print("Hello "+u+"!!");  
						%>
					</a></li>
				</ul>
			</nav>

			<h1> Add the new products here </h1> 

	

		<div class="form"> <center> <form action="AddProductToDatabase.jsp" method="post"><div class="form-elem"><label class="label">productid: </label><input type="text" class="field" name="productid"> 
		<br>
		<label class="label">retailer </label><input type="text" class="field" name="retailer"><br>
		<label class="label">category </label><input type="text" class="field" name="category"><br>
		<label class="label">name </label><input type="text" class="field" name="name"><br>
		<label class="label">price </label><input type="text" class="field" name="price"><br>
		
		<button class="AddProductToDatabase">Add Product</button></div> </center> </form>