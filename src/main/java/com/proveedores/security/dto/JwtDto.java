package com.proveedores.security.dto;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author Carlos
 */
public class JwtDto {

    private String token;
    private String bearer = "bearer";
    private String emailUsuario;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtDto(String token, String emailUsuario, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.emailUsuario = emailUsuario;
        this.authorities = authorities;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBearer() {
        return bearer;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

}
