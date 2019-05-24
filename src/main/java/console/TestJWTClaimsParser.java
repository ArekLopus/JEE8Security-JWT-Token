package console;

import java.util.Arrays;

import jwt.JWTStore;
import soteria.JWTCredential;
import token_generator.TokenUtilsConsole;

public class TestJWTClaimsParser {

	public TestJWTClaimsParser() {
		
		String token = TokenUtilsConsole.generateTokenConsoleSeconds("John Smith", Arrays.asList("user", "admin"), 120);
		
		JWTCredential parseTokenClaims = new JWTStore().getCredential(token);
		System.out.println(parseTokenClaims);
		
		System.out.println("Subject:" + parseTokenClaims.getSubject());
		System.out.println("Groups: " + parseTokenClaims.getGroups());
		
		System.out.println("--- Main Thread Finished ---");
	}

	public static void main(String[] args) {
		new TestJWTClaimsParser();

	}

}
