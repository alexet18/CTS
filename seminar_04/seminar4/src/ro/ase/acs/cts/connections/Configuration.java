package ro.ase.acs.cts.connections;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Configuration {

	public Configuration() {
		
	}
	
	public static String readConfiguration()
	{
		BufferedReader bufferedReader = null;
		try {
			FileInputStream fileInputStream = new FileInputStream("config.txt");
			InputStreamReader inputStreamReader =
					new InputStreamReader(fileInputStream);
			bufferedReader = new BufferedReader(inputStreamReader);
			return bufferedReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if(bufferedReader!=null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
