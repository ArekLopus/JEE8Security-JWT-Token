package an_info;

// https://developer.ibm.com/tutorials/j-javaee8-security-api-2/#method-execution-during-an-http-request

//-A client needs to login to get JWT token in response and pass it in every request in header 'Authorization: Bearer tokenGoesHere'


//-HttpAuthenticationMechanism is perfect to check and validate JWT Authorization hedaer.
//-HttpAuthenticationMechanism runs before any Filter or Servlet's methods, it also runs for every request.


// 1. Generate JWT token (authentication)
//	http://localhost:8080/JEE8Security-JWT-Token/res/login
//
// 2. Assing this token as requests 'Authorization: Bearer token' header and you can access proteced resource
//	http://localhost:8080/JEE8Security-JWT-Token/res/protected	// @RolesAllowed("admin")
//	http://localhost:8080/JEE8Security-JWT-Token/res/admin		// restraint set in web.xml


//		Possible extensions
//	Coookie version
//-login resource could return a cookie with value of JWT token that is then tested for existence and validation in HttpAuthenticationMechanism
//
// Time extension
//-After every response time could be added to the token but a client then needs to check Auth header (or cookie) every request to get renewed one.


public class Info {}
