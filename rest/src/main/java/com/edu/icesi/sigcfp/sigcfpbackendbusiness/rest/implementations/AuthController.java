package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.auth.model.AuthenticationRequest;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.auth.model.AuthenticationResponse;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.auth.model.ChangeUserPassword;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.auth.model.ValidateUserPassword;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.auth.security.MyUserDetailsService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.auth.services.implementations.JWTService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Userr;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations.UserrService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces.IAuthController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/auth")
//@CrossOrigin(origins = "*")
public class AuthController implements IAuthController {

    @Autowired
    private UserrService userrService; //Servicio de usuarios
    @Autowired
    private JWTService jwtService; //Servicio de JWT
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager; //Servicio de autenticacion
    @Autowired
    private PasswordEncoder passwordEncoder; //Servicio de encriptacion de contraseñas

    @Override
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        System.out.println(authenticationRequest.getUsername());
        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
            authenticationManager.authenticate(authentication);
            final String jwt = jwtService.create(authentication);
            return ResponseEntity.ok(new AuthenticationResponse(jwt));
        } catch (BadCredentialsException e) {
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
        userr.setUserPassword(passwordEncoder.encode(userr.getUserPassword()));
        userr.setEnable(true);
        userrService.addUserr(userr);
        return ResponseEntity.ok("User registered successfully");
    }

    @Override
    @PostMapping("/validatePassword")
    public ResponseEntity<?> validatePassword(@RequestBody ValidateUserPassword validateUserPassword) {
        Userr userr = userrService.findUserrByUserName(validateUserPassword.getUserName());
        if (userr != null && passwordEncoder.matches(validateUserPassword.getUserPassword(), userr.getUserPassword())) {
            return ResponseEntity.ok("La contraseña es correcta");
        }
        return ResponseEntity.badRequest().body("La contraseña es incorrecta");
    }

    @Override
    public ResponseEntity<?> recoveryPassword(String username, String password) {
        return null;
    }

    @Override
    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody ChangeUserPassword changeUserPassword) {
        Userr userr = userrService.findUserrByUserName(changeUserPassword.getUserName());
        if (userr != null && passwordEncoder.matches(changeUserPassword.getOldUserPassword(), userr.getUserPassword())
                && changeUserPassword.getNewUserPassword().equals(changeUserPassword.getConfirmPassword())) {
            userr.setUserPassword(passwordEncoder.encode(changeUserPassword.getNewUserPassword()));
            userrService.updateUserr(userr);
            return ResponseEntity.ok("Contraseña cambiada correctamente");
        }
        return ResponseEntity.badRequest().body("La contraseña es incorrecta");

    }

    @Override
    @PostMapping("sendEmailToTemporalPassword/{userName}")
    public ResponseEntity<?> sendEmailToTemporalPassword(@PathVariable String userName) {
        if (userrService.findUserrByUserName(userName)!=null){
            jwtService.sendEmailToTemporalPassword(userName);
            return ResponseEntity.ok("Contraseña temporal enviada");
        }
        return ResponseEntity.badRequest().body("Error al intentar cambiar la contraseña");
    }


}

