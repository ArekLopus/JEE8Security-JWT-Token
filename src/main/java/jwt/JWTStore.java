package jwt;

import java.util.HashSet;
import java.util.List;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import soteria.JWTCredential;
import token_generator.TokenUtilsConsole;

public class JWTStore {
	
	
    public String generateToken(String username, List<String> groups, int timeInSeconds) {
        
    		String token = TokenUtilsConsole.generateTokenConsoleSeconds(username, groups, timeInSeconds);
            return token;
    }
    
    
    @SuppressWarnings("unchecked")
    public JWTCredential getCredential(String token) {

    	Jws<Claims> parsedTokenClaims = TokenUtilsConsole.parseTokenClaims(token);
    	System.out.println("Parsed Token: " + parsedTokenClaims);
    	
    	if(parsedTokenClaims == null) {
    		return null;
    	}
    	
    	Claims body = parsedTokenClaims.getBody();

    	String subject = body.getSubject();
    	List<String> groups =  (List<String>) body.get("groups");
    	
    	return new JWTCredential(subject, new HashSet<String>(groups));
    }
}