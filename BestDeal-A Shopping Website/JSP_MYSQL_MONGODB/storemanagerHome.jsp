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
			
			<center> <form action=" addProduct.jsp"> <input type="submit" class="emphasized" value="Add a new Product "> </center>  </form> 

<% MySqlDataStoreUtilities mySqlDataStoreUtilities = new MySqlDataStoreUtilities();
				Connection connection = mySqlDataStoreUtilities.getConnection();
				HashMap<Integer,ArrayList<String>> fromDBHashMap=mySqlDataStoreUtilities.readProductFromDatabase();
				for (Map.Entry<Integer,ArrayList<String>> map1 : fromDBHashMap .entrySet())
			 {
			 	//String productid =map1.getKey() ;

				out.println("<h1>" + map1.getValue().get(1));
				out.println("<ul class=styledlist>");
				out.println("<li class=text>Company Name: "+map1.getValue().get(0)); out.println("</li>");
				out.println("<li class=text>Phone Name : "+map1.getValue().get(2)); out.println("</li>");
				out.println("<li class=text> Price:$"+map1.getValue().get(3));out.println("</li>");
				System.out.println("Id is "+map1.getKey());
				//out.println(" <div style=\"float:left;\"> <form  action=\" editProductServlet\"> name = \"prdName\" value =\""+ productid <button id=\"map1.getKey()\" >Edit</button> </div> </form> ");
				out.println("<form action = \"updateProduct.jsp\" method = \"post\">" + "<input type = \"hidden\" name = \"productid\" value =\""+ map1.getKey() + "\">" + "<button> updateProduct </button> </form>");out.println("<br>");

				out.println("<form action = \"deleteProduct.jsp\" method = \"post\">" + "<input type = \"hidden\" name = \"productid\" value =\""+ map1.getKey() + "\">" + "<button> deleteProduct </button> </form>");
				
				out.println("<br>");
				out.println("</ul class=styledlist>");
				out.println("</ul>");   
				out.println("</ul>");
				System.out.println("key=" + map1.getKey() + ", value=" + map1.getValue());
				

			}//End of FOr

				%>
				</body>
			</html>

