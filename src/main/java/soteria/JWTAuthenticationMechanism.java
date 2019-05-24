package soteria;

import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.identitystore.IdentityStore;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;

import jwt.JWTStore;

@ApplicationScoped
public class JWTAuthenticationMechanism implements HttpAuthenticationMechanism {

    @Inject
    IdentityStore identityStore;

    @Inject
    JWTStore jwtStore;
    
    
    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest req, HttpServletResponse res, HttpMessageContext context) throws AuthenticationException {
    	
    	final String bearerString = "Bearer ";
        String authorizationHeader = req.getHeader(HttpHeaders.AUTHORIZATION);
        Credential credential = null;
        
        if (authorizationHeader != null && authorizationHeader.startsWith(bearerString)) {      // 'Authentication: Bearer token' header exists
            
        	String token = authorizationHeader.substring(bearerString.length());
            credential = this.jwtStore.getCredential(token);
        }
        
        
        if (credential != null) {																// Token is valid
            return context.notifyContainerAboutLogin(this.identityStore.validate(credential));
        } else {																				// Token is NOT valid or no Authentication header
        	
        	if(context.isProtected()) {															// Accessed resource is protected (has constraints)
        		
        		try {																			// Generates custom response
        			context.getResponse().setStatus(401);
        			context.getResponse().getOutputStream().println("Unauthenticated Access Or Token Expired! Get JWT Token at: http://localhost:8080/JWTAndSoteria/res/login");
				} catch (IOException e) {
					e.printStackTrace();
					return context.responseUnauthorized();
				}
        		
        		return AuthenticationStatus.SEND_FAILURE;
        		
        	} else {																			// Accessed resource is not protected
        		return context.doNothing();	
        	}
        	
        }
        
    }
    
}