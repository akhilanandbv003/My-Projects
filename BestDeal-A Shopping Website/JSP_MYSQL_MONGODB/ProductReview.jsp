<!doctype html>

<!-- INDEX -->
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>BestDeal - Home</title>
<link rel="stylesheet" href="styles.css" type="text/css" />
</head>
<body>
	<div id="container">
		<header>
			<h1>
				<a href="/">Best<span>Deal</span></a>
			</h1>
			<h2>The Best Deals are here!!!!</h2>
			
			<form class="role-form" action="RoleIdentifier" method="post">
			<button type = "submit" name = "role" class="role" value = "SalesMan"> SalesMan</button>
			<button type = "submit" name = "role" class="role" value = "StoreManager">Store Manager</button>
			<button type = "submit" name = "role" class="role" value = "Customer">Customer</button>	
		</form>
			
		</header>
		
		<nav>
			<ul>
				<li class=""><a href="index.html">Home</a></li>
            <li class=""><a href="SmartPhones.html">SmartPhones</a></li>
            <li class=""><a href="Tablets.html">Tablets</a></li>
            <li class=""><a href="Laptop.html">Laptop</a></li>
            <li class=""><a href="TV.html">TV</a></li>
            <li class="start selected"><a href="">BestDeal's</a></li>
			</ul>
		</nav>
		
		<div class="border-page">
		<form action="insertReviewMongoDB.jsp" method="post">
		<div class="form-elem">
			<label class="label">Product Model Name </label> 
			<input type="text"  name="ProductName"> </br>
			
			<label class="label">Product Category </label> 
			<input type="text" class="review-field" name="ProductCategory"> </br>
			
			<label class="label">Product Price </label> 
			<input type="text" class="review-field" name="ProductPrice"> </br>
			
			<label class="label">Retailer Name </label> 
			<input type="text" class="review-field" name="RetailerName"> </br>
			
			<label class="label">Retailer ZipCode </label> 
			<input type="text" class="review-field" name="RetailerZip"> </br>
			
			<label class="label">Retailer City </label> 
			<input type="text" class="review-field" name="RetailerCity"> </br>
	
			<label class="label">Retailer State </label> 
			<input type="text" class="review-field" name="RetailerState"> </br>
			
			<label class="label">Product On Sale </label> 
			<input type="text" class="review-field" name="ProductOnSale"> </br>
			
			<label class="label">Manufacturer Name </label> 
			<input type="text" class="review-field" name="ManufacturerName"> </br>

			<label class="label">Manufacturer Rebate </label> 
			<input type="text" class="review-field" name="ManufacturerRebate"> </br>
			
			<label class="label">User ID </label> 
			<input type="text" class="review-field" name="UserID"> </br>
			
			<label class="label">User Age </label> 
			<input type="text" class="review-field" name="UserAge"> </br>
			
			<label class="label">User Gender </label> 
			<input type="text" class="review-field" name="UserGender"> </br>

			<label class="label">User Occupation </label> 
			<input type="text" class="review-field" name="UserOccupation"> </br>
			
			<label class="label">Review Rating (1-5) </label> 
			<input type="number" min="1" max="5" class="review-field" name="ReviewRating"> </br>
			
			<label class="label">Date </label> 
			<input type="date" class="review-field" name="Date"> </br>
			
			<label class="label">Review Text</label>
			<textarea rows="5" cols="40" class="textarea" name="ReviewText">
			</textarea></br>
			
			<button class="role"> SUBMIT REVIEW</button>
			</br>
		</div>
	</form>
	</div>
	
	</div>
</body>
</html>