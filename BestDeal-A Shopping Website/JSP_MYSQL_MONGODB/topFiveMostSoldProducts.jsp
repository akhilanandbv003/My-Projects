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
			MongoDBDataStoreUtilities mongoDBDataStoreUtilities = new MongoDBDataStoreUtilities();
			AggregationOutput output  = mongoDBDataStoreUtilities.gettopFiveMostSoldProducts();
			System.out.println("output is "+output);
			for (DBObject r :output.results()){
				System.out.println(r);
				System.out.println(r.get("_id"));
				System.out.println(r.get("countProduct"));
				
				out.println("<table border=\"1\">");
				out.println("<tr>");

				out.println("<td>");
				out.println("<b>Most Sold Product  "+r.get("_id"));
				out.println("</td>");

				out.println("<td>");
				out.println("<b> Number of Products Sold  "+r.get("countProduct"));
				out.println("</td>");

			}

			
			out.println("</tr>");
			out.println("</table>");
%>

		</body>
		</html>
