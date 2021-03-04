package ro.ase.acs.writers;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import ro.ase.acs.contracts.Writable;

public class FileWriter implements Writable{

	@Override
	public void print(String message) {
		// TODO Auto-generated method stub
		
		OutputStreamWriter osWriter=null;
		FileOutputStream fileOutputStream;
		try {
			fileOutputStream = new FileOutputStream("output.txt");
			osWriter=new OutputStreamWriter(fileOutputStream);
			osWriter.write(message);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(osWriter!=null) {
				
					try {
						osWriter.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			
			}
			
		}
	
	}
	
	
}
