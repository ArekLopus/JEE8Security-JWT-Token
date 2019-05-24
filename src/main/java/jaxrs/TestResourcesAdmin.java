package jaxrs;

import java.security.Principal;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

// http://localhost:8080/JEE8Security-JWT-Token/res/admin

//-Protected in web.xml descriptor.
@Path("/admin")
public class TestResourcesAdmin {
    
	@Inject
	Principal pr;
	
    @GET
    public Response echoWithJWTToken() {
    	
        return Response.ok().entity("This is a message from the admin resource. Principal: " + (pr == null ? "NULL" : pr.getName())).build();
    }
    
    
}
