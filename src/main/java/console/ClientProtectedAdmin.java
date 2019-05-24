package console;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ClientProtectedAdmin {
	
	public ClientProtectedAdmin() throws Exception {
		
		JsonObject user = Json.createObjectBuilder().add("username", "John Doe").add("password", "pass").build();
		
		Client cl = ClientBuilder.newClient();
		WebTarget loginTarget = cl.target("http://localhost:8080/JEE8Security-JWT-Token/res/login");
		WebTarget target = cl.target("http://localhost:8080/JEE8Security-JWT-Token/res/admin");
		
		Response loginResponse = loginTarget
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(user));
		
		String token = loginResponse.readEntity(String.class);
		System.out.println("Token: " + token);
		
		Response response = target
				.request()
				.header("Authorization", "Bearer " + token)
				.get();
		
		System.out.println("Response: " + response);
		System.out.println("Entity: " + response.readEntity(String.class));
		
	}

	public static void main(String[] args) {
		try {
			new ClientProtectedAdmin();
		} catch (Exception e) {
			System.out.println("Exception From Server: " + e.getMessage());
		}
	}
}
