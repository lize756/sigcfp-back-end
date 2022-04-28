package com.edu.icesi.sigcfp.sigcfpbackendbusiness.auth.services.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Userr;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.IOException;
import java.util.Collection;

public interface IJWTService {

    public String create(Authentication auth) throws JsonProcessingException;

    public boolean validate(String token);

    public Claims getClaims(String token);

    public String getUserName(String token);

    public long getUserId(String token);

    public String getUserRole(String token);

    public Userr getUserr(String token);

    public Collection<? extends GrantedAuthority> getRoles(String token) throws IOException;

    public String resolve(String token);

    public long getUserCompanyId(String token);

    public long getUserPersonId(String token);

    public boolean validatePassword(String token, String password);

    public void updatePassword(String token, String password);

}
