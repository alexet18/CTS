package ro.ase.acs.main;

import ro.ase.acs.contracts.Writable;
import ro.ase.acs.readers.Reader;
import ro.ase.acs.writers.ConsoleWriter;

public class Main {


	public static void main(String[] args) {
		Container ioc = new Container();
		
		ioc.Register(Readable.class, Reader.class);
		ioc.Register(Writable.class, ConsoleWriter.class);
		
		
		Orchestrator orchestrator = new Orchestrator(ioc.Resolve(Readable.class),ioc.Resolve(Writable.class));
		orchestrator.execute();
		
	}

}