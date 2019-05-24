package token_generator;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

public class TokenUtilsConsole {
	
	public static String generateTokenConsoleSeconds(String name, List<String> groups, long validTimeInMinutes) {
    	
    	Instant now = Instant.now();
    	
    	try {
            
    		Key key = KeyGeneratorSimple.generateKeyStatic();
            
    		String jwtToken = Jwts.builder()
    				.setIssuer("JWTAndSoteria")
                    .setSubject(name)
                    .setAudience("Soteria")
                    .claim("groups", groups)
                    .setIssuedAt(Date.from(now))
                    .setExpiration(Date.from(now.plus(validTimeInMinutes, ChronoUnit.SECONDS)))
                    //.signWith(key, SignatureAlgorithm.HS256)
                    .signWith(key)
                    .compact();
            
            System.out.println("---- TokenUtilsConsole.generateToken() - token generated: " + jwtToken + " - " + key);
            return jwtToken;
            
		} catch (Exception e) {
			
			System.out.println("--- TokenUtilsConsole.generateToken() - Exception: " + e.getMessage());
			return "Fail in TokenUtilsConsole.generateToken()";
			
		}
    }
	
	
    public static Jws<Claims> parseTokenClaims(String token) {
    	
    	Key key = KeyGeneratorSimple.generateKeyStatic();
    	
    	try {
    		
    		Jws<Claims> parsedClaimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(token);
    		return parsedClaimsJws;
    		
		} catch (Exception e) {
			System.out.println(" - Exception in TokenUtilsConsole.parseTokenClaims(): " + e.getMessage());
			return null;
		}
    }
    
    
    
	

    
    
    public static long tokenTimeToLive(String token) {
    	
    	try {
    		
    		Jws<Claims> parsedTokenClaims = TokenUtilsConsole.parseTokenClaims(token);
    		
    		if(parsedTokenClaims != null) {
    			Claims body = parsedTokenClaims.getBody();
    			int iat = (int) body.get("iat");
    			int exp = (int) body.get("exp");
    			System.out.println("Tokens live time (sec): " + (exp - iat));
    			
    			long tokensLeftTimeToLive = (body.getExpiration().getTime() - System.currentTimeMillis()) /1000;
    			System.out.println("Expires in (sec): " + tokensLeftTimeToLive);
    			
    			return tokensLeftTimeToLive; 
    		} else {
    			return -1;
    		}
    		
		} catch (Exception e) {
			System.out.println("Exception in TokenUtilsConsole.tokenTimeToLive(): " + e.getMessage());
			return -1;
		}
    	
	}
 
    public static String addTimeToToken(String token, long timeInSecondsToAdd) {
    	
    	Key key = KeyGeneratorSimple.generateKeyStatic();
    	
    	try {
    		
    		Jws<Claims> parsedClaimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(token);
    		Claims claims = parsedClaimsJws.getBody();
    		
    		String jwtToken = Jwts.builder()
    			.setClaims(claims)
    			.setExpiration(Date.from(Instant.now().plus(timeInSecondsToAdd, ChronoUnit.SECONDS)))
    			.signWith(key)
                .compact();
    			
            return jwtToken;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "Fail in TokenUtilsConsole.generateToken()";
		}
    }
        
}