package com.edu.icesi.sigcfp.sigcfpbackendbusiness.auth.model;

import java.io.Serializable;

/**
 * Clase que representa la respuesta de autenticación
 */
public class AuthenticationResponse implements Serializable {

    private final String jwt; // token de autenticación

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

}
