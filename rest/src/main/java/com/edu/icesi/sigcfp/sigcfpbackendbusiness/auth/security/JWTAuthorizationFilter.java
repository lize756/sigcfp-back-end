package com.edu.icesi.sigcfp.sigcfpbackendbusiness.auth.security;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.auth.services.implementations.JWTService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private JWTService jwtService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTService jwtService) {
        super(authenticationManager);
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(JWTService.HEADER_STRING);
        if (!requiresAuthentication(header)) {
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authenticationToken = null;
        if (jwtService.validate(header)){
            authenticationToken = new UsernamePasswordAuthenticationToken(jwtService.getUserName(header), null, jwtService.getRoles(header));
        }
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    protected boolean requiresAuthentication(String header) {
        if (header == null || !header.startsWith(JWTService.TOKEN_PREFIX)) {
            return false;
        }
        return true;
    }

}
