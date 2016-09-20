import java.io.*;
import java.util.Formatter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.ArrayList;

public class javasorting
{
    public static BufferedReader buf_read;
    public static String path_of_file; 
    public static String file_name;
    public static String file;
    public static String line;
    public static ArrayList<String> list_of_elements=null;
    public static long start =0;
    public static long end =0;
    public static long time_taken =0;
    public static Formatter fr;
 	
    public static void main(String[] args) 
    {
        buf_read = new BufferedReader(new InputStreamReader(System.in));
        try 
        {	
        	System.out.println("ENTER THE PATH OF FILE");
        	path_of_file = buf_read.readLine();
        	
        	System.out.println("ENTER THE FILE NAME");
        	file_name = buf_read.readLine();
        	
        	start = System.currentTimeMillis();
        	
        	file = path_of_file+"//"+file_name;
           
            buf_read= new BufferedReader(new FileReader(file));
            list_of_elements = new ArrayList<String>();
            
            while ((line = buf_read.readLine()) != null) 
            {  
                StringTokenizer token = new StringTokenizer(line);
              
                while (token.hasMoreTokens()) 
                {
                    String current_token = token.nextToken();
                    current_token = current_token.toLowerCase();
                    char[] token_array = current_token.toCharArray();
                    
                    for (int i = 0; i < token_array.length - 1; i++)
                    {
                        int index = i;
                        for (int j = i + 1; j < token_array.length; j++)
                            if (token_array[j] < token_array[index]) 
                                index = j;
                  
                        char temp = token_array[index];  
                        token_array[index] = token_array[i];
                        token_array[i] = temp;
                    }
                    
                    String word = new String(token_array);
                    list_of_elements.add(word);    
                 }
                
                try
            	{
        			fr = new Formatter(path_of_file+"/sort1MB-sharedmemory.txt");
        		}
        		catch(Exception e)	
            	{
        			System.err.println(e);    			
        		}
                
                fr.format("%s",list_of_elements);
                
                fr.close(); 
             }
            end = System.currentTimeMillis();
            time_taken  = end - start;
            System.out.println("Time elapsed : " + time_taken +" milliseconds");
            System.out.println("Time elapsed : " + time_taken/1000.0 +" seconds");
         }	
        catch (FileNotFoundException e) 
        {
            System.err.println(e);
        } 
        catch (IOException e1) 
        {
        	System.err.println(e1);
        }  
    }    	
}
    
   
