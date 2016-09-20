
import java.io.File;
import java.io.FileNotFoundException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;



public class MySQL_Connector {
	

	static MySQL_Connector msqco = new MySQL_Connector();

	static String tpcFilePath = System.getProperty("user.dir")+File.separator +"resources" ;

	static String createFilePath = System.getProperty("user.dir")+File.separator +"resources";
	
	static String analyzeFilePath = System.getProperty("user.dir")+File.separator +"resources"; 
	
	static ArrayList<String> check = new ArrayList<String>() ;

	public static void main(String[] args) throws SQLException {

	//	System.out.println("******************************************************************************************************** MySQL JDBC Connection Testing ********************************************************************************************************");

	//	System.out.println(" ");

		System.out.println(" The CURRENT WORKING DIRECTORY : " + System.getProperty("user.dir"));

		msqco.runIt();

	}
	
	
	
	
	public void runIt()  {

		System.out.println(" ");
		
		
		System.out.println("*************************************************************************************************************");
		System.out.println("*                                                                                                           *");
		System.out.println("*                                                 TPC-H RUN                                                 *");
		System.out.println("*                                                                                                           *");
		System.out.println("*************************************************************************************************************");

		System.out.println(" ");
		
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("....................................................................................... Connection Details...........................................................................");
			System.out.println("Enter HOST ID : ");
			String host = sc.next().trim() ;
			System.out.println("Enter PORT NUMBER : ");
			String portNo = sc.next().trim();
			System.out.println("Enter DATABASE NAME : ");
			String databaseName = sc.next().trim();
			System.out.println("Enter USER NAME : ");
			String userName = sc.next().trim();
			System.out.println("Enter PASSWORD : ");
			String passWord = sc.next().trim();

			Connection connection = msqco.connect2MySQL(host,portNo, databaseName, userName, passWord);

			Statement stmt;
			try {
				stmt = connection.createStatement();
				boolean occurs = false ;
				int flag = 0 ;
				try{
				occurs = stmt.execute("Select count(*) from REGION");
				}catch(SQLException e){
					flag = 1 ;
					//e.printStackTrace();
				}
				ResultSet rs = null ;
				if(occurs == true){
				rs = stmt.executeQuery("Select count(*) from REGION");
				}
				if (occurs == true) {
					
					if(rs.next()){
					
					String result = rs.getString(1).trim();

					if (result.equals("0")) {
						System.out.println(" ");
						System.out.println("....................................................................................... Tables are EMPTY .......................................................................................");
						System.out.println("");
						System.out.println("Enter the PATH to the FOLDER that contains the DATA to LOAD : ");
						String folderpath = sc.next().trim();
						msqco.loadTables(stmt, folderpath);
						System.out.println(" ");
						System.out.println("....................................................................................... ALL TABLES LOADED SUCCESFULLY .......................................................................................");
						System.out.println("");
						msqco.analyzeTables(stmt,analyzeFilePath );
						double[][] queryTimings = msqco.executeTPCH(stmt, tpcFilePath);
						msqco.printAvgTime(queryTimings);
						break;
					}

					else {
						System.out.println(" ");
						System.out.println("....................................................................................... Tables are NOT EMPTY Executing TPC-H QUERIES .......................................................................................");
						double[][] queryTimings = msqco.executeTPCH(stmt, tpcFilePath);
						msqco.printAvgTime(queryTimings);
						break;

					}

				} 
				}else if(flag ==1){
					
					System.out.println(" ");
					System.out.println("....................................................................................... Tables are ABSENT need to CREATE THEM .......................................................................................");
					msqco.createTables(stmt, createFilePath);
					System.out.println("Enter the PATH to the FOLDER that contains the DATA to LOAD : ");
					String folderpath = sc.next().trim();
					msqco.loadTables(stmt, folderpath);
					System.out.println(" ");
					System.out.println("....................................................................................... ALL TABLES LOADED SUCCESFULLY .......................................................................................");
					System.out.println("");
					//System.out.println("....................................................................................... Analyzing TABLES .............................................................................");
					msqco.analyzeTables(stmt,analyzeFilePath );
					double[][] queryTimings = msqco.executeTPCH(stmt, tpcFilePath);
					msqco.printAvgTime(queryTimings);
					break;

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}



		}
		sc.close();

	}
	
	/**
	 * This method establishes the connection between  JDBC and MySql.
	 * @param port - The port number on which MySql works on
	 * @param databaseName - The name of the database to connect to.
	 * @param username - The user name of the database
	 * @param password - The password used by the user to create the database
	 * @return  A JDBC connection object.
	 */

	public Connection connect2MySQL(String host,String port, String databaseName, String username, String password) {

		// System.out.println("jdbc:mysql://localhost:" + port+"/" +
		// databaseName + "," + username + ","+ password);

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return null;

		}

		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://"+ host +":" + port + "/" + databaseName, username,
					password);

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}

		if (connection != null) {
			System.out.println("....................................................................................... CONNECTED TO MYSQL .......................................................................................");
		} else {
			System.out.println("Failed to make connection!");
		}

		System.out.println("");
		return connection;

	}

	@SuppressWarnings({ "resource" })
	public String readFile(String filepath) {
		
		String content = null ;
		
		try {
			 content = new Scanner(new File(filepath)).useDelimiter("\\Z").next();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return content ;

	}

	public void loadTables(Statement stmt, String folderpath) {
		System.out.println(" ");
		System.out.println("....................................................................................... Loading Tables .......................................................................................");

		try {

			String[] tablefileName = { "part.tbl'", "region.tbl'", "nation.tbl'", "supplier.tbl'", "partsupp.tbl'",
					"customer.tbl'", "orders.tbl'", "lineitem.tbl'" };
			String[] tableName = { "PART", "REGION", "NATION", "SUPPLIER", "PARTSUPP", "CUSTOMER", "ORDERS",
					"LINEITEM" };

			for (int i = 0; i < tableName.length; i++) {
				boolean l1 = stmt.execute("load data infile " + "'" + folderpath + File.separator + tablefileName[i]
						+ " into table " + tableName[i] + " columns terminated by " + "'|'");
				if (l1 == false) {
					System.out.println("Table " + tableName[i] + " Loaded Successfully... ");
				} 
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("");

	}

	public void createTables(Statement stmt, String filepath) {
		System.out.println(" ");
		System.out.println("....................................................................................... Creating Tables .......................................................................................");

		String queries = msqco.readFile(filepath + File.separator + "create.txt");

		String[] queriesList = queries.split(";");
		for (int i = 0; i < queriesList.length; i++) {
			if (queriesList[i].trim().isEmpty()== false && queriesList[i].trim().equals(" ") == false) {

				boolean rs = true;
				try {
					//System.out.println(queriesList[i]);
					rs = stmt.execute(queriesList[i]);
					//System.out.println(rs);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (rs == false) {
					System.out.println("Table Created Successfully");
				}
			}
		}

		System.out.println("");

	}
	
	

	public void analyzeTables(Statement stmt, String folderpath) {
		System.out.println(" ");
		System.out.println("....................................................................................... Analyzing Tables .......................................................................................");

		String queries = msqco.readFile(folderpath + File.separator + "analyze.txt");

		String[] queriesList = queries.split(";");
		for (int i = 0; i < queriesList.length; i++) {
			if (queriesList[i].trim().isEmpty()== false && queriesList[i].trim().equals(" ") == false) {

				boolean rs;
				try {
					rs = stmt.execute(queriesList[i]);
					if (rs != false) {
						System.out.println("Table Analyzed Successfully");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
			}
		}

		System.out.println("");


	}
	
	
	

	public double[][] executeTPCH(Statement stmt, String filepath) throws SQLException {
		
		System.out.println(" ");
		System.out.println("....................................................................................... Executing TPC-H QUERIES .......................................................................................");
		System.out.println(" ");
		String queries = msqco.readFile(filepath + File.separator + "tpch.txt");
		String[] queriesList = queries.split(";");
		//System.out.println("query --- " + queriesList[queriesList.length-1]);
		ResultSet[] rsa = new ResultSet[queriesList.length];
		double[][] queryTimings = new double[queriesList.length][3];
		
		for (int i = 0; i < queriesList.length; i++) {
			//System.out.println(queriesList[i].trim());
			//System.out.println(i);
			if (queriesList[i].trim().isEmpty()== false && queriesList[i].trim().equals(" ")== false ) {
				
				if(queriesList[i].trim().startsWith("CREATE")== false && queriesList[i].trim().startsWith("DROP") == false){
					for (int j = 0; j < 3; j++) {
						System.out.println("****************************");
						System.out.println("RUNNING QUERY : " + (i+1) );
						System.out.println("--------------------------");
						System.out.println(" Run Number : " + (j + 1)  );
						System.out.println("****************************");
						System.out.println(" ");
						System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
						double start = System.currentTimeMillis();
						rsa[i] = stmt.executeQuery(queriesList[i]+";");
						msqco.printTable(rsa[i]);
						double end = System.currentTimeMillis();
						double elapsedTime = end - start;
						queryTimings[i][j] = elapsedTime;
						System.out.println("------------------------------------------");
						System.out.println(" Elapsed Time (milliseconds) : " + elapsedTime);
						System.out.println("------------------------------------------");
						System.out.println(" ");
					}
				}
				else {
					boolean temp = stmt.execute(queriesList[i]+";");
					check.add(queriesList[i]+";");
				}
//				else {
//					rsa[i] = stmt.executeQuery(queriesList[i]+";");
//				}

			}
			
			System.out.println(" ");
		}
		System.out.println("");
		return queryTimings;

	}
	
	
	

	public void printRow(ResultSet rs, int columnIndex) {

		try {
			String row = rs.getString(columnIndex);
			System.out.print(row);
			System.out.print("\t\t");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	public void printTable(ResultSet rs) {
		System.out.println(" ");
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int colNo = rsmd.getColumnCount();

			for (int i = 1; i <= colNo; i++) {

				System.out.print(rsmd.getColumnName(i));
				System.out.print("\t\t");

			}
			System.out.println("");
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

			while (rs.next()) {

				for (int j = 1; j <= colNo; j++) {
					msqco.printRow(rs, j);
				}
				System.out.println("");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public void printAvgTime(double[][] queryTimings) {
		
		System.out.println(" ");

		double avgSum = 0.0;

		System.out.println("...................................................................................... The Timings for TPC-H QUERIES ........................................................................");

		for (int i = 0; i < queryTimings.length; i++) {

			System.out.println("QUERY NO : " + (i + 1) );

			double sum = 0.0;

			for (int j = 0; j < queryTimings[0].length; j++) {

				sum += queryTimings[i][j];

			}

			double avg = sum / 3.0;

			avgSum += avg;

			System.out.println("Avg Execution Time (3 runs) for Query : " + (i + 1) + " ------> " + ((Math.round( avg * 100.0 ) / 100.0 ))/1000);

		}
		
		System.out.println("************************************************************************************************************************************************");

		System.out.println(
				" Total Elapsed Time for TPC-H queries : " + ((Math.round( avgSum * 100.0 ) / 100.0 ))/1000.0);
		
		System.out.println("************************************************************************************************************************************************");

//		System.out.println(check.size());
//		for (int h = 0 ; h<check.size();h++){
//			System.out.println(h + "--->" + check.get(h).trim());
//		}
		
	}

}
