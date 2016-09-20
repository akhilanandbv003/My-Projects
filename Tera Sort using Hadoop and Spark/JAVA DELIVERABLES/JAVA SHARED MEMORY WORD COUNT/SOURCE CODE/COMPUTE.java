import java.io.IOException;


public class COMPUTE extends Thread 
{
	String z;
	public COMPUTE(String sam)
	{
		z=sam;
	}
	@Override
	public void run() 
	{
		String[] splitted =z.split(" ");
		for (int i=0; i<splitted.length ; i++) 
		{
			if ((MAINCLASS.hashmap.get(splitted[i])) != null) 
			{
				int value = ((Integer) MAINCLASS.hashmap.get(splitted[i]));
				MAINCLASS.hashmap.put(splitted[i], value + 1);
			} 
			else 
			{
				MAINCLASS.hashmap.put(splitted[i], 1);
			}
		}

	}

}
