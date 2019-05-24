package jaxrs;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@DeclareRoles({"admin", "user", "dev"})
@ApplicationPath("/res")
public class JAXRSConfig extends Application {}