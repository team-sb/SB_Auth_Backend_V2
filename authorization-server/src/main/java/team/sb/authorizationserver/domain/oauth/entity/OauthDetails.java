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

    @Column(columnDefinition = "char(50)")
    private String webServerRedirectUri;

    @Column(columnDefinition = "char(20)", nullable = false)
    private String authorizedType;

    @Column(columnDefinition = "char(10)", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role authorities;

    public OauthDetails(String clientId, String clientSecret, String authorizedType, String scope) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.scope = scope;
        this.authorizedType = authorizedType;
        this.authorities = Role.ROLE_USER;
    }

    public void updateRedirectUri(String redirectUri) {
        this.webServerRedirectUri = redirectUri;
    }

}
