import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class READ 
{
	public static Thread []threadss=new Thread[8];
	public static int r=0;
	public static String sample;
	public static void readdata()
	{
		int read = 0, reading_length = MAINCLASS.PART_SIZE;
		byte[] part;
		
			try {
				MAINCLASS.fd = new FileInputStream(MAINCLASS.file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while (MAINCLASS.file_size > 0) 
			{
				part = new byte[reading_length];
				try {
					read = MAINCLASS.fd.read(part, 0, reading_length);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				MAINCLASS.file_size -= read;
				assert (read == part.length);
				sample=new String(part);
				//System.out.println("--------"+sample);
				//COMPUTE t=new COMPUTE(sample);
				threadss[r]=new COMPUTE(sample);
				threadss[r].start();
				try {
					threadss[r].join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				part = null;
				r++;
			}
			try {
				MAINCLASS.fd.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
