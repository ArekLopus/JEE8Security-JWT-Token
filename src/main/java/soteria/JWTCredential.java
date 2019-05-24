package soteria;

import java.util.Set;

import javax.security.enterprise.credential.Credential;

public class JWTCredential implements Credential {

    private String subject;
    private Set<String> groups;

    public JWTCredential(String subject, Set<String> groups) {
        this.subject = subject;
        this.groups = groups;
    }


    public String getSubject() {
        return subject;
    }

    public Set<String> getGroups() {
        return groups;
    }

}