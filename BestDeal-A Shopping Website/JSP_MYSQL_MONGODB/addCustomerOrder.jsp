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
			String productid =  request.getParameter("productid");
			HashMap<Integer,ArrayList<String>> fromDBHashMap=mySqlDataStoreUtilities.queryDatabase(productid);

			%>
			
			
			<%
for (Map.Entry<Integer,ArrayList<String>> map1 : fromDBHashMap .entrySet())
			{
				out.println("<h1>" + map1.getValue().get(1));
				out.println("<form action=\" orderConfirmation.jsp\" method = \"post\">");

				out.println("<ul class=styledlist>");
				
				out.println("<li class=text>Company Name:<input type = \"text\" name = \"retailer\" value =\""+ map1.getValue().get(0) + "\" />"); 
				out.println("</li></span>");//retailer
				out.println("<li class=text>Phone Name :<input type = \"text\" name = \"name\" value =\""+ map1.getValue().get(2)+ "\" />");
				out.println("</li></span>");//phone
				out.println("<li class=text> Price:$<input type = \"text\" name = \"price\" value =\""+ map1.getValue().get(3)+ "\" />");
				out.println("</li></span>");//price



				out.println("Shipping Address <input type=\"text\" name = \"address\" class=\"field\"  >");
				out.println("<br>");
				out.println("Credit Card Details <input type=\"text\" name = \"carddetails\"class=\"field\" >");
				out.println("<br>");
				out.println("Quantity <input type=\"text\" name = \"quantity\"class=\"field\" >");
				out.println("<br>");

				out.println("<input type = \"hidden\" name = \"productid\" value =\""+ map1.getKey() + "\">" + "<button> Confirm Order  </button> </form>");

				out.println("<br>");
				out.println("</ul class=styledlist>");
				out.println("</ul>");   
				out.println("</ul>");
				System.out.println("key=" + map1.getKey() + ", value=" + map1.getValue());
			}//End of FOr


%>
			
			</body>
		</html>