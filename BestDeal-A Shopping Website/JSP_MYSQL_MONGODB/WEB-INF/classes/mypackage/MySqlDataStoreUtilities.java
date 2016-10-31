package mypackage ;
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.*;
import java.sql.*;

public class MySqlDataStoreUtilities

{
	Connection connection = null;
	public Connection getConnection()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");

			String string = "jdbc:mysql://localhost:3306/bestdeal";
			String string2 = "root";
			String string3 = "root";
			
			boolean bl = false;
			System.out.println("Connecting to a selected database...");


			connection = DriverManager.getConnection(string, string2, string3);


			System.out.println("Connected database successfully...");

			

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return connection;
	}//ENd of getConnection
	

	public ResultSet getLoginDetails(){
		ResultSet resultSet = null;
		try
		{
			Statement statement = connection.createStatement();
			String string4 = "select * from logindetails";
			resultSet = statement.executeQuery(string4);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return resultSet;


	}//ENd of getLoginDetails
	public void insertUser(String username, String password,String phone,String email,String role)
	{
		String insertIntoCustomerRegisterQuery = "INSERT INTO logindetails(username,password,phone,email,role)"
		+ "VALUES (?,?,?,?,?);";
		try{
			PreparedStatement pst =connection.prepareStatement(insertIntoCustomerRegisterQuery);
			pst.setString(1,username);
			pst.setString(2,password);
			pst.setString(3,phone);
			pst.setString(4,email);
			pst.setString(5,role);
			pst.execute();


		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
}//ENd of insertUser

public HashMap<Integer,ArrayList<String>> readProductFromDatabase(){
	HashMap<Integer,ArrayList<String>> fromDBHashMap=new HashMap<Integer,ArrayList<String>> ();
	try
	{	

		String productdetailsQuery = "select * from productdetails";
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(productdetailsQuery);				
		ResultSetMetaData md = rs.getMetaData();
		int columns = md.getColumnCount();
		while (rs.next())
		{
			ArrayList<String> a = new ArrayList<String>();
			a.add(rs.getString("retailer") );
			a.add(rs.getString("category") );
			a.add(rs.getString("name"));
			a.add(rs.getString("price"));
			fromDBHashMap.put((Integer) rs.getObject("productid"), a);
				}//End of while

			}//End of try 
			catch(Exception ex)
			{
				ex.printStackTrace();
				System.out.println("SQL Exception!!");
			}
			return fromDBHashMap;

	}//ENd of readProductFromDatabase
	public void insertProduct (String productid, String retailer,String category,String name,String price){
		String insertintoproductdetails = "insert into productdetails(productid , retailer, category ,  name , price) VALUES (?,?,?,?,?); ";
		try{
			PreparedStatement pst =connection.prepareStatement(insertintoproductdetails);
			pst.setString(1,productid);
			pst.setString(2,retailer);
			pst.setString(3,category);
			pst.setString(4,name);
			pst.setString(5,price);
			pst.execute();


		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}//ENd of insertProduct

	public void deleteProduct (String productid)
	{
		try
		{
			Statement stmt = connection.createStatement();
			String query ="DELETE FROM productdetails WHERE productid="+productid;
			//out.println("<h1> "+query+" </h1> ");
			stmt.execute(query);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("SQL Exception!!");
		}
	}//End of deleteProduct

	public HashMap<Integer,ArrayList<String>> queryDatabase (String productid)
	{
		HashMap<Integer,ArrayList<String>> fromDBHashMap = new HashMap<Integer ,ArrayList<String>>();
		try
		{
			Statement statement = connection.createStatement();
			String productdetailsQuery = "Select * from  productdetails WHERE productid="+productid;

			ResultSet rs = statement.executeQuery(productdetailsQuery);
			ResultSetMetaData md = rs.getMetaData();
			int columns = md.getColumnCount();
			while (rs.next())
			{
				ArrayList<String> a = new ArrayList<String>();
				a.add(rs.getString("retailer") );
				a.add(rs.getString("category") );
				a.add(rs.getString("name"));
				a.add(rs.getString("price"));
				fromDBHashMap.put((Integer) rs.getObject("productid"), a);
			}

		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("SQL Exception!!");
		}
		return fromDBHashMap;
	}//End of deleteProduct

	public void updateProduct (String productid, String retailer,String name,String price){
		String productdetailsQuery = "update productdetails set retailer='"+retailer+"',name='"+name+"',price="+price+" WHERE productid="+productid;
		try
		{	
			Statement statement = connection.createStatement();			
			statement.executeUpdate(productdetailsQuery);				
			

		}

		catch(SQLException se){
      //Handle errors for JDBC
			se.printStackTrace();
		}

	}//ENd of updateProduct

	public HashMap<String,ArrayList<String>> getAllOrders (){
		HashMap<String,ArrayList<String>> fromDBHashMap = new HashMap<String ,ArrayList<String>>();

		try
		{
			Statement statement = connection.createStatement();
			String query ="Select * FROM orders;";
			//System.out.println("Shopping Cart Home try");

			ResultSet rs = statement.executeQuery(query);

			ResultSetMetaData md = rs.getMetaData();
			int columns = md.getColumnCount();
			while (rs.next())
			{
				ArrayList<String> a = new ArrayList<String>();
				a.add(rs.getString("username") );
				a.add(rs.getString("itemname") );
				a.add(rs.getString("quantity"));
				a.add(rs.getString("price"));
				a.add(rs.getString("datepurchased"));
				a.add(rs.getString("orderid"));
				a.add(rs.getString("orderstatus"));

				fromDBHashMap.put(rs.getString("orderid"), a);
			}//End of while


		}//End of try 

		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("SQL Exception!!");
		}
		return fromDBHashMap;
}//ENd of getAllOrders
public void deleteOrder(String orderid)
{
	try
	{
		Statement stmt = connection.createStatement();
		String query ="DELETE FROM orders WHERE orderid="+orderid;
			//out.println("<h1> "+query+" </h1> ");
		stmt.execute(query);
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
		System.out.println("SQL Exception!!");
	}
	}//End of deleteProduct



	public void updateOrders(String username, String itemname,String quantity,String price,String datepurchased,String orderid,String orderstatus){
		try
		{	
			Statement statement = connection.createStatement();			
			
			String orderdetailsQuery = "update orders set username='"+username+"',itemname='"+itemname+"',quantity='"+quantity+
			"',price='"+price+" ',datepurchased=' "+datepurchased+" ',orderid=' "+orderid+"',orderstatus='"+orderstatus+"' WHERE orderid="+orderid;
			
			statement.executeUpdate(orderdetailsQuery);				
			

		}

		catch(SQLException se){
      //Handle errors for JDBC
			se.printStackTrace();
		}


}//End of updateOrders

public void insertOrders (String username, String itemname,String quantity,String price,String datepurchased,String orderid,String orderstatus){
	String insertintoproductdetails = "insert into orders(username , itemname, quantity ,price,  datepurchased , orderid,orderstatus) VALUES (?,?,?,?,?,?,?); ";
	try{

		MySqlDataStoreUtilities connobj = new MySqlDataStoreUtilities();
		Connection conn= connobj.getConnection();
		PreparedStatement pst =conn.prepareStatement(insertintoproductdetails);
		pst.setString(1,username);
		pst.setString(2,itemname);
		pst.setString(3,quantity);
		pst.setString(4,price);
		pst.setString(5,datepurchased);
		pst.setString(6,orderid);
		pst.setString(7,orderstatus);
		pst.execute();
		}//End of Try

		
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("SQL Exception!!");
		}
}//End of insertOrders

public HashMap<String,ArrayList<String>> getOrderDetails (String username){
	String query ="Select * FROM orders WHERE username='"+username+"';";
	HashMap<String,ArrayList<String>> fromDBHashMap = new HashMap<String ,ArrayList<String>>();
	try
	{
		Statement statement = connection.createStatement();		


		ResultSet rs = statement.executeQuery(query);
		System.out.println("Querying shopping cart ");
		ResultSetMetaData md = rs.getMetaData();
		int columns = md.getColumnCount();
		while (rs.next())
		{
			ArrayList<String> a = new ArrayList<String>();
			a.add(rs.getString("username") );
			a.add(rs.getString("orderid") );
			a.add(rs.getString("itemname"));
			a.add(rs.getString("price"));
			a.add(rs.getString("orderstatus"));

			fromDBHashMap.put(rs.getString("orderid"), a);
			}//End of while


		}//End of try 

		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("SQL Exception!!");
		}
		return fromDBHashMap;
}//End of getOrderDetails


public void addToCart(String productid,String username)
{
	String query ="Select * FROM productdetails WHERE productid="+productid;
	try
	{
		Statement statement = connection.createStatement();	
		ResultSet rs1 = statement.executeQuery(query);
		System.out.println(query);
		System.out.println("Running addToCart!!");
		while (rs1.next())
		{
			Statement statement1 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			String itemname= rs1.getString("name");
				String price = rs1.getString("price");
				
				
			String shoppingcartquery = "insert into  shoppingcart" + "(itemname, price, productid, username)" + "values('" +itemname+"','"+price +"', '"+productid+"' ,'"+username+"' ) " ;
			System.out.println(shoppingcartquery);
			statement1.executeUpdate(shoppingcartquery);
		}//End of while ,'"+quantity+"'
	}//End of try
	catch(Exception ex)
	{
		ex.printStackTrace();
		System.out.println("SQL Exception!!");
	}//End of catch 

}//ENd of addToCart




public HashMap<String,ArrayList<String>> queryFromCart(String username )
{
	String query ="Select * FROM shoppingcart WHERE username='"+username+"';";
	HashMap<String,ArrayList<String>> fromDBHashMap = new HashMap<String ,ArrayList<String>>();
	try
			{
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(query);
				//Put things in hashmap 
				ResultSetMetaData md = rs.getMetaData();
				int columns = md.getColumnCount();
				while (rs.next())
				{
					ArrayList<String> a = new ArrayList<String>();
					a.add(rs.getString("username") );
					a.add(rs.getString("productid") );
					a.add(rs.getString("itemname"));
					a.add(rs.getString("price"));
					fromDBHashMap.put(rs.getString("productid"), a);
				}//End of while
			}//End of try 
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("SQL Exception!!");
		}
		return fromDBHashMap;
}//ENd of queryFromCart


public void removeFromCart(String productid){
try
			{
			System.out.println("Inside removeFromCart");		
			Statement stmt = connection.createStatement();
			String query ="DELETE FROM shoppingcart WHERE productid="+productid;	
			System.out.println(query);		
			stmt.execute(query);
		}
		catch(Exception ex)
			{
				ex.printStackTrace();
				System.out.println("SQL Exception!!");
			}


}//ENd of queryFromCart
public HashMap<String,ArrayList<String>> getAllShoppingCart(String username)
{
	String query ="Select * FROM shoppingcart where username='"+username+"';";
	HashMap<String,ArrayList<String>> fromDBHashMap = new HashMap<String ,ArrayList<String>>();
	try
			{
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(query);
				//Put things in hashmap 
				ResultSetMetaData md = rs.getMetaData();
				int columns = md.getColumnCount();
				while (rs.next())
				{
					ArrayList<String> a = new ArrayList<String>();
					a.add(rs.getString("username") );
					a.add(rs.getString("productid") );
					a.add(rs.getString("itemname"));
					a.add(rs.getString("price"));
					fromDBHashMap.put(rs.getString("productid"), a);
				}//End of while
			}//End of try 
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("SQL Exception!!");
		}
		return fromDBHashMap;
}//ENd of getAllShoppingCart

}//End of class
