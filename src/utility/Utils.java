package utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Contains miscellaneous utility functions to assist with manipulating game data

public class Utils 
{
	
	public static String loadFileAsString(String path)
	{
		StringBuilder builder = new StringBuilder();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;	// current file line
			while ((line = br.readLine()) != null)
				builder.append(line + "\n");
			
			br.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return builder.toString();
	}
	
	public static int parseInt(String number)
	{
		try {
			return Integer.parseInt(number);
		} catch(NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
}
