package ro.ase.acs.writers;

import ro.ase.acs.contracts.Writable;

public class ConsoleWriter implements Writable {
	
	@Override
	public void print(String message)
	{
		System.out.println(message);
	}
	
}
