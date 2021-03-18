package ro.ace.acs.cts.logger;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;

public class Logger {

	private String fileName;
	public Logger()
	{
		this.fileName = "log.txt";
	}
	
	public Logger(String fileName)
	{
		this.fileName = fileName;
	}
	
	public void log(String message)
	{
		try {
			FileOutputStream file = new FileOutputStream(fileName,true);
			OutputStreamWriter writer = new OutputStreamWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			
			bufferedWriter.write(String.valueOf(new Date()+ " "));
			bufferedWriter.write(message);
			bufferedWriter.write(System.lineSeparator());
			bufferedWriter.close();
			writer.close();
			file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
