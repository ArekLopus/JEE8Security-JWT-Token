package soteria;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

//Normally we would validate credentials here, but we know JWT is valid so we only return CredentialValidationResult with subject and groups
@ApplicationScoped
public class JWTIdentityStore implements IdentityStore {

    @Override
    public CredentialValidationResult validate(Credential credential) {
        
    	if (credential instanceof JWTCredential) {
            // It is valid, called by JWTAuthenticationMechanism.context.notifyContainerAboutLogin(this.identityStore.validate(credential));
            JWTCredential jwtCredential = (JWTCredential) credential;

            return new CredentialValidationResult(jwtCredential.getSubject(), jwtCredential.getGroups());
        }

        return CredentialValidationResult.INVALID_RESULT;
    }

}