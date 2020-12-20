package com.proveedores.security.jwt;

import com.proveedores.security.entity.UsuarioPrincipal;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 *
 * Generador de tokens
 */
@Component
public class JwtProvider {

    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}") //Valor del archivo properties
    private String secret;
    
    @Value("${jwt.expiration}") //Valor del archivo properties
    private Integer expiration;

    public String generateToken(Authentication authentication) {
        UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();
        return Jwts.builder().setSubject(usuarioPrincipal.getUsername())
                .setIssuedAt(new Date()) //Fecha/tiempo de inicio de la expiracion
                .setExpiration(new Date(new Date().getTime() + expiration))//Fecha/tiempo expiracion
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getUserNameFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).
                parseClaimsJws(token).getBody().getSubject(); //getSubject, es el userName
    }

    public boolean isValidToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).
                    parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException me) {
            logger.error("Token mal formado.");
        } catch (UnsupportedJwtException ue) {
            logger.error("Token no soportado.");
        } catch (ExpiredJwtException ee) {
            logger.error("Token expirado.");
        } catch (IllegalArgumentException ia) {
            logger.error("Token vacio.");
        } catch (SignatureException se) {
            logger.error("Error en la firma.");
        }
        return false;
    }
}
