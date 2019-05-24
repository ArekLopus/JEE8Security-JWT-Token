package console;

import java.util.Arrays;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import token_generator.TokenUtilsConsole;

public class ClientProtectedServletFail {
	
	public ClientProtectedServletFail() throws Exception {
		
		String token = TokenUtilsConsole.generateTokenConsoleSeconds("John Smith", Arrays.asList("admin"), 3);
		
		Client cl = ClientBuilder.newClient();
		WebTarget target = cl.target("http://localhost:8080/JEE8Security-JWT-Token/servlet");
		
		Response response = target
				.request()
				.header("Authorization", "Bearer " + token)
				.get();
		
		System.out.println("Response: " + response);
		System.out.println("Entity: " + response.readEntity(String.class));
		
		
		System.out.println("\nWaiting for token getting expired...");
		Thread.sleep(3000);
		
		String st2 = target
				.request()
				.header("Authorization", "Bearer " + token)
				.get(String.class);
		
		System.out.println("Response: " + st2);
	}

	public static void main(String[] args) {
		try {
			new ClientProtectedServletFail();
		} catch (Exception e) {
			System.out.println("Exception From Server: " + e.getMessage());
		}
	}
}
