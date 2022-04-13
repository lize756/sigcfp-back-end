package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.auth.model.AuthenticationRequest;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.auth.model.AuthenticationResponse;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.auth.security.MyUserDetailsService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.auth.services.implementations.JWTService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Userr;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.IUserrRepo;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces.IAuthController;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController()
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController implements IAuthController {

    @Autowired private IUserrRepo iUserrRepo;
    @Autowired private JWTService jwtService;
    @Autowired private MyUserDetailsService userDetailsService;
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private PasswordEncoder passwordEncoder;


    @Override
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
            authenticationManager.authenticate(authentication);
            final String jwt = jwtService.create(authentication);
            return ResponseEntity.ok(new AuthenticationResponse(jwt));
        }
        catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body("Invalid username or password");
        }

    }



    @Override
    public ResponseEntity<?> logout() {
        return null;
    }




    @Override
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Userr userr) {
        try {
            // Encoding Password using Bcrypt
            String encodedPass = passwordEncoder.encode(userr.getUserPassword());
            // Setting the encoded password
            userr.setUserPassword(encodedPass);
            // Persisting the User Entity to H2 Database
            userr = iUserrRepo.save(userr);
            Authentication authentication = new UsernamePasswordAuthenticationToken(userr.getUserName(), userr.getUserPassword());
            authenticationManager.authenticate(authentication);
            // Generating the JWT Token
            final String jwt = jwtService.create(authentication);
            // Responding with JWT
            return ResponseEntity.ok(new AuthenticationResponse(jwt));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error while registering user");
        }
    }

}
