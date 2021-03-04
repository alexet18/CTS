package ro.ase.acs.readers;

import java.io.Closeable;
import java.io.IOException;

import java.util.Scanner;
import ro.ase.acs.contracts.Readable;

public class Reader implements Closeable,Readable {
	private Scanner scanner =new Scanner(System.in);
	
	@Override
	public  String read() {
		
		System.out.print("Message: ");
		String message = scanner.nextLine();
		return message;
	}
	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		scanner.close();
	}
}
