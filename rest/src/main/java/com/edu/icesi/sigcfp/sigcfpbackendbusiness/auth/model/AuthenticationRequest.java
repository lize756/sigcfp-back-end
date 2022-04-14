package com.edu.icesi.sigcfp.sigcfpbackendbusiness.auth.model;


import java.io.Serializable;

/**
 * Clase para la autenticacion de usuarios
 */
public class AuthenticationRequest implements Serializable {

    private String username; // Nombre de usuario
    private String password; // Contraseña del usuario

    //need default constructor for JSON Parsing
    public AuthenticationRequest() {
    }
    public AuthenticationRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
