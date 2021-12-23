package team.sb.authorizationserver.domain.oauth.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OauthDetails {

    @Id
    @Column(columnDefinition = "char(11)")
    private String clientId;

    @Column(columnDefinition = "char(11)", nullable = false)
    private String clientSecret;

    @Column(columnDefinition = "char(5)", nullable = false)
    private String scope;

    @Column(columnDefinition = "char(50)")
    private String webServerRedirectUri;

    @Column(columnDefinition = "char(20)", nullable = false)
    private String authorizedGrantTypes;

    @Column(columnDefinition = "char(10)", nullable = false)
    private String authorities;

    public OauthDetails(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.scope = "read";
        this.authorizedGrantTypes = "code, refresh_token";
        this.authorities = "ROLE_USER";
    }

    public void updateRedirectUri(String redirectUri) {
        this.webServerRedirectUri = redirectUri;
    }

}
