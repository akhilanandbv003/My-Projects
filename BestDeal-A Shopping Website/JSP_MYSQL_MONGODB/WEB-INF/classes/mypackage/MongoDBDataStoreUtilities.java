 package mypackage ;
 import java.io.*; 
 import javax.servlet.*;
 import javax.servlet.http.*;
 import java.util.*;
 import java.sql.*;
 import com.mongodb.*;
 public class MongoDBDataStoreUtilities 

 {
 	DBCollection myReviews;

 	public void getConnection()
 	{
 		try
 		{

 			MongoClient mongo;
 			mongo = new MongoClient("localhost", 27017);
 			DB db = mongo.getDB("CustomerReviews");
 			myReviews= db.getCollection("myReviews");
 		}//End of try



 		catch (Exception e)
 		{
 			e.printStackTrace();
 			System.out.println("exception occured");
}//ENd of catch
}

public  void insertReviews(Map<String, String[]> requestMap , String ProductName,String ProductCategory,
	String ProductPrice ,String RetailerName,String RetailerZip,String RetailerCity,String RetailerState,String ProductOnSale,String ManufacturerName,String ManufacturerRebate,String
	UserID ,String UserAge,String UserGender,String UserOccupation,String Date,String ReviewText)

{	
	try
	{
		getConnection();

		System.out.println("Inside InsertReviewMongoDBServlet try block");

		BasicDBObject dbo = new BasicDBObject("title","myReviews");
		System.out.println(dbo);
		for (Map.Entry<String, String[]> reviews : requestMap.entrySet()) 		
			dbo.append(reviews.getKey(), reviews.getValue()[0].toString());	
		System.out.println(dbo);



		myReviews.insert(dbo);
		System.out.println("Inserting to DB success" );
}//End of try



catch (Exception e)
{
	e.printStackTrace();
	System.out.println("exception occured");
}//ENd of catch
}//ENd of insertReviews


public  DBCursor viewReviews(String ProductName){	
	System.out.println(ProductName);
	DBCursor cursor=null;
	try
	{
		getConnection();
		BasicDBObject whereQuery = new BasicDBObject();

		whereQuery.put("ProductName", ProductName);

		 cursor = myReviews.find(whereQuery);
		System.out.println("CUrsor from MongoDB is "+cursor);


}//End of try

catch (Exception e)
{
	e.printStackTrace();
	System.out.println("exception occured");
}//ENd of catch
return 	cursor;
}//ENd of viewReviews



public  DBCursor getTopFiveLikedProducts(){	
	//System.out.println(ProductName);
	DBCursor cursor=null;
	try
	{
		getConnection();
		BasicDBObject whereQuery = new BasicDBObject();

		cursor = myReviews.find().sort(new BasicDBObject ("ReviewRating",-1)).limit(5);	

		 
		System.out.println("CUrsor from MongoDB is "+cursor);


}//End of try

catch (Exception e)
{
	e.printStackTrace();
	System.out.println("exception occured");
}//ENd of catch
return 	cursor;
}//ENd of getTopFiveLikedProducts


public  AggregationOutput gettopFiveMostSoldProducts(){	
	//System.out.println(ProductName);
	AggregationOutput output=null;
	try
	{
		getConnection();
		DBObject group =new BasicDBObject("$group",new BasicDBObject("_id","$ProductName").append("countProduct", new BasicDBObject("$sum",1)));

			DBObject limit =new BasicDBObject("$limit",5);		
			DBObject sort =new BasicDBObject("$sort",new BasicDBObject("countProduct",-1));
 output = myReviews.aggregate(group,sort,limit);



}//End of try

catch (Exception e)
{
	e.printStackTrace();
	System.out.println("exception occured");
}//ENd of catch
return 	output;
}//ENd of gettopFiveMostSoldProducts

public  AggregationOutput gettopFiveZipCodesProductsSold(){	
	//System.out.println(ProductName);
	AggregationOutput output=null;
	try
	{
		getConnection();
		DBObject group =new BasicDBObject("$group",new BasicDBObject("_id","$RetailerZip").append("countProduct", new BasicDBObject("$sum",1 )));
			DBObject limit =new BasicDBObject("$limit",5);

			DBObject sort =new BasicDBObject("$sort",new BasicDBObject("countProduct",-1));
 output = myReviews.aggregate(group,sort,limit);



}//End of try

catch (Exception e)
{
	e.printStackTrace();
	System.out.println("exception occured");
}//ENd of catch
return 	output;
}//ENd of gettopFiveMostSoldProducts


 }//End of class
