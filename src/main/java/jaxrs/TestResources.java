package jaxrs;

import java.security.Principal;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

// http://localhost:8080/JEE8Security-JWT-Token/res/public
// http://localhost:8080/JEE8Security-JWT-Token/res/protected

@Path("/")
public class TestResources {
    
	@Inject
	Principal pr;
	
	@GET
    @Path("public")
    public Response echo() {
		
        return Response.ok().entity("This is a message from the public resource.").build();
    }
    
	
    @GET
    @Path("protected")
    @RolesAllowed("admin")
    public Response echoWithJWTToken() {
    	
        return Response.ok().entity("This is a message from the protected resource. Principal: " + (pr == null ? "NULL" : pr.getName())).build();
    }
    
    
}
