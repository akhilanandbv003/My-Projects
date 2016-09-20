import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.lang.Object;


public class MAINCLASS 
{
	public static int PART_SIZE = 0;
	public static String path_of_file=null, file_name=null;
	public static String holder=null;
	public static int number_of_threads=0; 
	public static long start=0;
	public static long end=0;
	public static long time_taken=0;
	public static HashMap <String,Integer>hashmap = new HashMap<String,Integer>();	
	public static File file;
	public static int i=0;
	public static int p=0;
	public static FileInputStream fd;
	public static int file_size=0;
	
	public static void main(String args[])
	{
		String newFileName;
		FileOutputStream filePart;
		
		//Enter the path of file
		System.out.println("ENTER THE PATH : ");
		BufferedReader path_reader = new BufferedReader(new InputStreamReader(System.in));
		try 
		{
			path_of_file = path_reader.readLine().trim();
		}
		catch (IOException e) 
		{
			System.out.println("Path not found");
		}
		
		//Enter the file name
		System.out.println("ENTER THE FILENAME");
		Scanner inputReader = new Scanner(System.in);
		file_name=inputReader.nextLine();
		
		//Complete file path
		file = new File(path_of_file + "//" + file_name);
		
		//File Size
		file_size=(int) file.length();
		
		try 
		{
			fd = new FileInputStream(file);
		} 
		catch (FileNotFoundException e) 
		{
			System.err.println(e);
		}
		
		System.out.println("Enter the number of threads(1-8)");
		Scanner in = new Scanner(System.in);
		number_of_threads = in.nextInt();
		
		PART_SIZE= (file_size/number_of_threads);
		start=System.currentTimeMillis();
		
		READ.readdata();
	
		Iterator<String> key =  hashmap.keySet().iterator();
	    Iterator<Integer> value = hashmap.values().iterator();
	    FileWriter writer=null;
		try 
		{
			writer = new FileWriter(path_of_file+"/wordcount-java.txt");
		} 
		catch (IOException e) 
		{
			System.err.println(e);
		}
	    while( key.hasNext())
	    {
	    	//System.out.println("ENTERED");
	    	String str1 = key.next().toString();
	    	String str2 = value.next().toString();
	    	try 
	    	{
				writer.write(str1);
			}
	    	catch (IOException e) 
	    	{
	    		System.err.println(e);
			}
	    	try 
	    	{
				writer.write("========================================");
			} 
	    	catch (IOException e) 
	    	{
	    		System.err.println(e);
			}
	    	try 
	    	{
				writer.write(str2);
			}
	    	catch (IOException e) 
	    	{
	    		System.err.println(e);
	    	}
	      }
	      try 
	      {
			writer.close();
	      }
	      catch (IOException e) 
	      {
	    	  System.err.println(e);
	      }
	      end=System.currentTimeMillis( );
	      
	      //System.out.println("******************HASHMAP********************");
	      //System.out.println(hashmap);
	      time_taken=end-start;
	      System.out.println("Timetaken:" + time_taken + "milliseconds" );
	      System.out.println("SUCCESS!!!!!!!!");
	      
	}
}
