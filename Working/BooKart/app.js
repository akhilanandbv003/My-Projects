console.log("Beforeee!!");

var myApp = angular.module("myApp",["ngRoute"]);

myApp.config(function($routeProvider){
	$routeProvider
	.when("/books",{
	templateUrl:"partials/book-list.html",	
	controller:"BookListCtrl"
	})
	.when("/kart",{
	templateUrl:"partials/kart-list.html"	,
	controller:"KartListCtrl"
	})
	.otherwise({
		redirectTo:"/books"
	});
	
});

myApp.factory("kartService",function(){
	var kart=[];
	
	return{
		getKart:function(){
			return kart;
			
		},
		addToKart:function(book){
			kart.push(book);
		},
		buy:function(book){
			alert("Thanks for buying : ", book.name);
		}
	};
	
});
myApp.factory("bookService",function(){
	var books=[
	{
		imgUrl:"1.jpeg",
		name:"Adultery",
		price:205,
		rating:4,
		binding:"Paperback",
		publisher:"Random House India",
		releaseDate:"12-08-2014",
		details:"Details of the book Adultery by Paulo Coelho "
	},
	{
		imgUrl:"2.jpeg",
		name:"Geronimo Stilton Spacemice #1: Alien Escape",
		price:168,
		rating:5,
		binding:"Paperback",
		publisher:"Other  House India",
		releaseDate:"3-03-2017",
		details:"Meet Geronimo Stiltonix"
	}
	];
	return{
		getBooks: function(){
			return books;
		},
		addToKart:function(){
			
		}
	};
	
});
myApp.controller("KartListCtrl",function($scope,kartService){
	$scope.kart=kartService.getKart();
	
	$scope.buy =function(book){
		kartService.buy(book);
	};
});

	
	
	
myApp.controller("HeaderCtrl",function($scope){
	$scope.appDetails={
		title:"BooKart",
		tagline:"We have more than 1 Million Books for you"
	};
}
);

myApp.controller("BookListCtrl",function($scope,bookService,kartService){
	$scope.books=bookService.getBooks();
	
	$scope.addToKart=function(book){
		kartService.addToKart(book);
	};
}
);


console.log("Sucess!!");