
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

			<h3>Create a New Customer Account ?</h3>
			<form action="onlineUserRegistration.jsp" method="post">
				<div class="form-elem">
					<label class="label">Username </label> 
					<input type="text"class="field" name="username" > 
					<label class="label">Password</label> 
					<input type="password" class="field" name="password" >

					<label class="label">Phone Number </label> 
					<input type="text"class="field" name="phone" > 

					<label class="label">Who are you </label>  <select name ="role"> 
		<option value="Customer">Customer</option>
		<option value="Salesman">Salesman</option>
		 <option value="StoreManager">StoreManager</option>
		 </select>

					
					<label class="label">Email </label> 
					<input type="text"class="field" name="email" > 
					<button class="signup">Create User account </button>
				</div>
				</form>

		


			
			</body>
			</html>