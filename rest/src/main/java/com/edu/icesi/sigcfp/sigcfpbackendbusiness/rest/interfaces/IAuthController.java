package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.auth.model.AuthenticationRequest;
import org.springframework.http.ResponseEntity;

public interface IAuthController {

    public ResponseEntity<?> login(AuthenticationRequest authenticationRequest) throws Exception;
    public ResponseEntity<?> login(String username, String password);
    public ResponseEntity<?> logout();

}
