package team.sb.authorizationserver.domain.oauth.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.sb.authorizationserver.global.security.auth.type.Role;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OauthDetails {

    @Id
    @Column(columnDefinition = "char(11)")
    private String clientId;

    @Column(columnDefinition = "char(11)", nullable = false)
    private String clientSecret;

    @Column(columnDefinition = "char(30)", nullable = false)
    private String scope;

    @Column(columnDefinition = "char(50)", nullable = false)
    private String webServerRedirectUri;

    @Column(columnDefinition = "char(20)")
    private String authorizedType;

    @Column(columnDefinition = "char(10)", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role authorities;

    public OauthDetails(String clientId, String clientSecret, String redirectUri, String scope) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.scope = scope;
        this.webServerRedirectUri = redirectUri;
        this.authorities = Role.ROLE_USER;
    }

    public void setAuthorizedType(String authorizedType) {
        this.authorizedType = authorizedType;
    }

}
