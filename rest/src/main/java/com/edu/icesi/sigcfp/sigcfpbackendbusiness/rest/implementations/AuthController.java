package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.auth.model.AuthenticationRequest;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.auth.model.AuthenticationResponse;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.auth.security.MyUserDetailsService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.auth.services.implementations.JWTService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces.IAuthController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController implements IAuthController {

    //@Autowired private JWTUtil jwtUtil;
    @Autowired private JWTService jwtService;
    @Autowired private MyUserDetailsService userDetailsService;
    @Autowired private AuthenticationManager authenticationManager;


    @Override
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
       // final String jwt = jwtUtil.generateToken(userDetails);
        //return ResponseEntity.ok(new AuthenticationResponse(jwt));

        return null;

    }



    @Override
    public ResponseEntity<?> login(String username, String password) {
        return null;
    }

    @Override
    public ResponseEntity<?> logout() {
        return null;
    }


}
