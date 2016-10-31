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
			<HashMap class="sidebar">
				<ul>
					<li><a href="Trending.jsp">Trending and Data Analytics</a></li>
					<li><a href="orderStatus.jsp">Check the status of your order </a></li>
				</ul>

				<% MySqlDataStoreUtilities mySqlDataStoreUtilities = new MySqlDataStoreUtilities();
				Connection connection = mySqlDataStoreUtilities.getConnection();
				HashMap<Integer,ArrayList<String>> fromDBHashMap=mySqlDataStoreUtilities.readProductFromDatabase();

				%>

				<%

				for (Map.Entry<Integer,ArrayList<String>> map1 : fromDBHashMap .entrySet())
					{
				out.println("<h1>" + map1.getValue().get(1));
				out.println("<ul class=styledlist>");
				out.println("<li class=text>Company Name: "+map1.getValue().get(0)); out.println("</li>");
				out.println("<li class=text>Phone Name : "+map1.getValue().get(2)); out.println("</li>");
				out.println("<li class=text> Price:$"+map1.getValue().get(3));out.println("</li>");

				out.println("<form action = \"buyNow.jsp\" method = \"post\">" + "<input type = \"hidden\" name = \"productid\" value =\""+ map1.getKey() + "\">" + "<button> Buy Now</button> </form>");
				out.println("<br>");
				out.println("<form action = \"ProductReview.jsp\" method = \"post\">" + "<input type = \"hidden\" name = \"productid\" value =\""+ map1.getKey() + "\">" + "<button> WriteReview </button> </form>");
				out.println("<br>");
				out.println("<form action = \"viewReview.jsp\" method = \"post\">" + "<input type = \"hidden\" name = \"productname\" value =\""+ map1.getValue().get(2)+ "\">" + "<button>View Review  </button> </form>");
				out.println("<br>");
				out.println("<form action = \"addToCart.jsp\" method = \"post\">" + "<input type = \"hidden\" name = \"productid\" value =\""+ map1.getKey() + "\">" + "<button> Add to cart </button> </form>");
				//out.println("  <div style=\"float:left;\"> <form action=\" addToCartServlet\"> <input type=\"submit\" value=\"Add to cart \"> </div> </form> ");
				out.println("<br>");
				out.println("</ul class=styledlist>");
				//out.println("</ul>");   
				//out.println("</ul>");
				System.out.println("key=" + map1.getKey() + ", value=" + map1.getValue());
			}//End of FOr

			%>

		</body>
		</html>