package com.edu.icesi.sigcfp.sigcfpbackendbusiness.auth.security;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.auth.services.implementations.JWTService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Userr;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.IUserrService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase que se encarga de la autenticación de usuarios
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager; //variable de tipo AuthenticationManager
    @Autowired
    private JWTService jwtService; //variable de tipo JWTService
    @Autowired
    private IUserrService userrService; //variable de tipo IUserrService
    @Autowired
    private MyUserDetailsService myUserDetailsService; //variable de tipo MyUserDetailsService
    private long userId = 0L; //variable de tipo long que almacena el id del usuario

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTService jwtService) {
        this.authenticationManager = authenticationManager;
        // TODO: Cambiar este path como una constante
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/auth/login", "POST"));
        //setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/auth/register", "POST"));
        this.jwtService = jwtService;
    }

    @Override
    /**
     * Método que se encarga de la autenticación de usuarios
     */
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String userName = obtainUsername(request);
        String userPassword = obtainPassword(request);
        if (userName != null || userPassword != null) {
            // TODO: Borrar esto despues de probar
            logger.info("userName: " + userName + " userPassword: " + userPassword + " from form data");
        } else {
            Userr userr = null;
            try {
                userr = new ObjectMapper().readValue(request.getInputStream(), Userr.class);
                userName = userr.getUserName();
                userPassword = userr.getUserPassword();
                // TODO: Borrar esto despues de probar
                logger.info("userName: " + userName + " userPassword: " + userPassword + " from request json body");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        userName = userName.trim();

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName, userPassword);
        // TODO: Borrar esto después de probar
        System.out.println("UserDetail: " + authenticationToken.getDetails());

        return authenticationManager.authenticate(authenticationToken);
    }


    @Override
    /**
     * Método que se encarga de la autenticación exitosa de los usuarios
     */
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        String token = jwtService.create(authResult);

        response.addHeader(JWTService.HEADER_STRING, JWTService.TOKEN_PREFIX + token);
        Map<String, Object> body = new HashMap<String, Object>();
        //body.put("userId", jwtService.getUserId(token));
        body.put("user", ((User) authResult.getPrincipal()));
        body.put("userCompanyId", jwtService.getUserCompanyId(token));
        body.put("userPersonId", jwtService.getUserPersonId(token));
        body.put("token", token);
        //body.put("userName", jwtService.getUserName(token));
        //body.put("rolees", jwtService.getRoles(token));
        //body.put("rolee", jwtService.getUserRole(token));
        //body.put("userr", jwtService.getUserr(token));
        //body.put("message", String.format("Hola %s, has iniciado sesión correctamente", jwtService.getUserName(token)));
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(200);
        response.setContentType("application/json");

    }

    @Override
    /**
     * Método que se encarga de la autenticación fallida de los usuarios
     */
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        Map<String, Object> body = new HashMap<String, Object>();
        body.put("message", "Error de autenticación: username o password incorrectos");
        body.put("error", failed.getMessage());
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(401);
        response.setContentType("application/json");
    }
}
