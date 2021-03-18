package ro.ase.acs.cts.connections;

public class RestApiDevelopmentV2 extends RestServiceV2 {

	public RestApiDevelopmentV2(String serverUrl) {
		super(serverUrl);
		System.out.println("Development server v2");
	}

	@Override
	public void connect() {
		System.out.println("Connected succesfully to " + serverUrl);
	}

}
