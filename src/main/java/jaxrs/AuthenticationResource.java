package jaxrs;

import java.util.Arrays;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import jwt.JWTStore;

// http://localhost:8080/JEE8Security-JWT-Token/res/login

@Path("/login")
public class AuthenticationResource {

    @Inject
    JWTStore jwtStore;

    @POST
    public Response authenticate(JsonObject credential) {
        
        String username = credential.getString("username");
        //String password = credential.getString("password");

        // Groups should be retrieved from a DB based on an authenticated user.
        String token = this.jwtStore.generateToken(username, Arrays.asList("admin", "user"), 120);

        //return Response.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + token).build();
        return Response.ok(token).build();
    }
    
    @GET
    public String test() {
        return "Just Test";
    }
}