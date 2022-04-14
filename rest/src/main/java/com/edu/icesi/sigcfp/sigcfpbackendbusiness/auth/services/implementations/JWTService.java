package com.edu.icesi.sigcfp.sigcfpbackendbusiness.auth.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.auth.security.SimpleGrantedAuthorityMixin;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.auth.services.interfaces.IJWTService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

@Component
public class JWTService implements IJWTService {

    public static final String SECRET_KEY = Base64Utils.encodeToString("Est@.Es.la.Secret-Key".getBytes()); // Clave secreta para generar el token
    public static final Long EXPIRATION_DATE = 3600000 * 24L; // Tiempo de expiracion del token
    public static final String TOKEN_PREFIX = "Bearer "; // Prefix del token
    public static final String HEADER_STRING = "Authorization"; // Header para el token

    @Override
    /**
     * Genera un token para el usuario
     */
    public String create(Authentication auth) throws JsonProcessingException {
        String userName = ((User) auth.getPrincipal()).getUsername();

        Collection<? extends GrantedAuthority> rolees = auth.getAuthorities();

        Claims claims = Jwts.claims();
        claims.put("roles", new ObjectMapper().writeValueAsString(rolees));
        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY.getBytes())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_DATE))
                .compact();
        return token;
    }

    @Override
    /**
     * Valida el token
     */
    public boolean validate(String token) {
        try {
             getClaims(token);
             return true;
        }catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    /**
     *
     */
    public Claims getClaims(String token) {
        Claims claims= Jwts.parser()
                // TODO: cambiar este clave por una constante
                .setSigningKey(SECRET_KEY.getBytes())
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody();
        return claims;
    }

    @Override
    /**
     * Obtener el usuario del token
     */
    public String getUserName(String token) {
        return getClaims(token).getSubject();
    }

    @Override
    /**
     * Obtener los roles del token
     */
    public Collection<? extends GrantedAuthority> getRoles(String token) throws IOException {
        Object roles = getClaims(token).get("roles");
        Collection<? extends GrantedAuthority> rolees = Arrays.asList(new ObjectMapper()
                .addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityMixin.class)
                .readValue(roles.toString().getBytes(), SimpleGrantedAuthority[].class));
        return rolees;
    }

    @Override
    /**
     * Obtener el token
     */
    public String resolve(String token) {
        if (token != null && token.startsWith(TOKEN_PREFIX)) {
            return token.replace(TOKEN_PREFIX, "");
        }
        return null;
    }
}
