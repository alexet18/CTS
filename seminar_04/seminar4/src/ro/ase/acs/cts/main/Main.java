package ro.ase.acs.cts.main;

import ro.ace.acs.cts.logger.LoggerSingleton;
import ro.ase.acs.cts.connections.Configuration;
import ro.ase.acs.cts.connections.RestService;
import ro.ase.acs.cts.connections.RestServiceV2;
import ro.ase.acs.cts.connections.ServerType;
import ro.ase.acs.cts.connections.SimpleFactory;
import ro.ase.acs.cts.connections.SimpleFactoryV2;

public class Main {

	
	public static void main(String[] args) {
		//Logger logger = new Logger();
		//logger.log("Main ended seminar_04");
		
		LoggerSingleton logger = LoggerSingleton.getInstance();
		logger.log("Main singleton");
		
//		RestApiRelease rest = new RestApiRelease();
//		rest.connect();
		
		RestService rest = SimpleFactory.createConnection(ServerType.DEVELOPMENT);
		rest.connect();
		
		RestServiceV2 restV2 = SimpleFactoryV2.createConnection(
				Configuration.readConfiguration(),"https:./.");
		
		restV2.connect();
		
	}
}
