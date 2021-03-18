package ro.ase.acs.cts.connections;

public class SimpleFactory {

	public SimpleFactory()
	{
		
	}
	
	public static RestService createConnection(ServerType type)
	{
		
		if(type == ServerType.RELEASE)
		{
			return new RestApiRelease();
		}
		else if(type == ServerType.DEVELOPMENT)
		{
			
			return new RestApiDevelopment();
		}
		
		return null;
		
	}
	
}
