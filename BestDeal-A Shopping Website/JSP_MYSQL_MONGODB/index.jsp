<!doctype html>

<!-- INDEX -->
<html>

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
				<li class="end"><a href="#">BestDeal</a></li>
			</ul>
		</nav>
	
		<img class="header-image" src="images/bestdeals.jpg" width="100%"
			height="100%" alt="Index Page Image" />
			<div class="form">
			<h3>Login Using Your Credentials</h3>
			<form action="ValidateUser.jsp" method="post">
				<div class="form-elem">
					<label class="label">Username </label> 
					<input type="text" class="field" name="username" value="c">
					<!-- <input type="text" class="field" name="username" value="s"> -->
					<!-- <input type="text" class="field" name="username" value="st"> -->

					<label class="label">Password </label> 
					<input type="password" class="field" name="password" value="c">
					<!-- <input type="password" class="field" name="password" value="s"> -->
					<!-- <input type="password" class="field" name="password" value="st"> -->

					<label class="label">Who are you </label>  <select name ="role"> 
		<!-- <option value="Customer">Customer</option> -->
		<!-- Testing Customer --> 
		<option value="Customer" selected="true ">Customer</option>

		<option value="Salesman">Salesman</option>
		<!-- Testing Salesman -->
		<!-- <option value="Salesman"selected="true" >Salesman</option> -->

		 <option value="StoreManager" >StoreManager</option>
		 <!-- Testing storemananger -->
		<!--  <option value="StoreManager" selected="true">StoreManager</option> -->
		 </select>
					<button class="login">Login</button>
				</div>
			</form>
			<br> <br>

			
				<h3>Create a New Account ?</h3>
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
					<button class="signup">Sign Up</button>
				</div>
				</form>
		</div>
	</div>
</body>
</html>