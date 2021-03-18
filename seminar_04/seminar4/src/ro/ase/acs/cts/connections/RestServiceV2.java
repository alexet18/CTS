package ro.ase.acs.cts.connections;

public abstract class RestServiceV2 {

	protected String serverUrl;
	
	public RestServiceV2(String serverUrl) {
		this.serverUrl = serverUrl;
	}
	
	public abstract void connect();
	
}
