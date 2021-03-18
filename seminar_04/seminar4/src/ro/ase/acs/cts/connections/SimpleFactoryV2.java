package ro.ase.acs.cts.connections;

public class SimpleFactoryV2 {

	
	public static RestServiceV2 createConnection(String type,String url)
	{
		if("development".equals(type)) {
			return new RestApiDevelopmentV2(url);
		}
		else if("release".equals(type)) {
			return new RestApiReleaseV2(url);
		}
		else {
			return null;
		}
			
	}
}
