package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.auth.model.AuthenticationRequest;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.auth.model.ChangeUserPassword;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.auth.model.ValidateUserPassword;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Userr;
import org.springframework.http.ResponseEntity;

public interface IAuthController {
    public ResponseEntity<?> login(AuthenticationRequest authenticationRequest) throws Exception;

    public ResponseEntity<?> logout();

    public ResponseEntity<?> register(Userr userr);

    public ResponseEntity<?> validatePassword(ValidateUserPassword validateUserPassword);

    public ResponseEntity<?> recoveryPassword(String username, String password);

    public ResponseEntity<?> changePassword(ChangeUserPassword changeUserPassword);
}


