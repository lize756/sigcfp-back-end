package com.edu.icesi.sigcfp.sigcfpbackendbusiness.auth.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.auth.security.SimpleGrantedAuthorityMixin;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.auth.services.interfaces.IJWTService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Noti;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Userr;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.IUserrService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Component
public class JWTService implements IJWTService {

    public static final String SECRET_KEY = Base64Utils.encodeToString("Est@.Es.la.Secret-Key".getBytes()); // Clave secreta para generar el token
    public static final Long EXPIRATION_DATE = 3600000 * 24L; // Tiempo de expiracion del token
    public static final String TOKEN_PREFIX = "Bearer "; // Prefix del token
    public static final String HEADER_STRING = "Authorization"; // Header para el token
    @Autowired
    private IUserrService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    private MimeMessage mimeMessage;
    private MimeMessageHelper helper;

    @Override
    /**
     * Genera un token para el usuario
     */
    public String create(Authentication auth) throws JsonProcessingException {

        String userName = ((User) auth.getPrincipal()).getUsername();

        long userId = userService.findUserrByUserName(userName).getUserId();
        //String rolee = userService.findUserrByUserName(userName).getRolee().getRoleName();
        //Userr userr = userService.findUserrByUserName(userName);
        //userr.setUserPassword(null);

        long userPersonId = (userService.findPersonByUserName(userName) != null) ? userService.findPersonByUserName(userName).getPersId() : -1; // Verifica que el usuario tenga asociado una persona para obtener su id
        long userCompanyId = (userService.findCompanyByUserName(userName) != null) ? userService.findCompanyByUserName(userName).getCompId() : -1; // Verifica que el usuario tenga asociado un compañia para obtener su id

        Collection<? extends GrantedAuthority> rolees = auth.getAuthorities();

        Claims claims = Jwts.claims();

        claims.put("rolees", new ObjectMapper().writeValueAsString(rolees));
        claims.put("userId", userId);
        claims.put("userCompanyId", userCompanyId);
        claims.put("userPersonId", userPersonId);
        //claims.put("userr", userr);
        //claims.put("rolee", rolee);

        String token = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY.getBytes())
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_DATE))
                .compact();

        return token;
    }

    @Override
    /**
     * Valida el token
     */
    public boolean validate(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    /**
     *
     */
    public Claims getClaims(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes())
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody();
        return claims;
    }

    @Override
    /**
     * Obtener el usuario del token
     */
    public String getUserName(String token) {
        return getClaims(token).getSubject();
    }

    @Override
    public long getUserId(String token) {
        return getClaims(token).get("userId", Long.class);
    }

    @Override
    public String getUserRole(String token) {
        return getClaims(token).get("rolee", String.class);
    }

    @Override
    public Userr getUserr(String token) {
        return getClaims(token).get("userr", Userr.class);
    }

    @Override
    public long getUserCompanyId(String token) {
        return getClaims(token).get("userCompanyId", Long.class);
    }

    @Override
    public long getUserPersonId(String token) {
        return getClaims(token).get("userPersonId", Long.class);
    }

    @Override
    public boolean validatePassword(String userName, String password) {
        Userr userr = userService.findUserrByUserName(userName);
        if (userr != null) {
            return passwordEncoder.matches(password, userr.getUserPassword());
        }
        return false;
    }


    @Override
    public void updatePassword(String userName, String password) {
        Userr userr = userService.findUserrByUserName(userName);
        if (userr != null) {
            userr.setUserPassword(passwordEncoder.encode(password));
            userService.updateUserr(userr);
        }
    }


    @Override
    public void sendEmailToTemporalPassword(String userName) {

        Userr userr = userService.findUserrByUserName(userName);

        String temporalPass = UUID.randomUUID().toString();
        String emailSubject = "Cambio de contraseña";
        String emailMessage = "<br/>Hola. " +
                "<br/>Esta es su nueva contraseña temporal:" + temporalPass +
                "<br/>Por favor, cámbiela una vez acceda a la plataforma" +
                "<br/> <br/>Equipo SIGCFP"
                ;
        String passEncoded = passwordEncoder.encode(temporalPass).toString();

        if (userr != null) {

            userr.setUserPassword(passEncoded);
            userService.updateUserr(userr);

            try {
                helper.setTo(userr.getUserName());
                helper.setText(emailMessage, true);
                helper.setSubject(emailSubject);
                mailSender.send(mimeMessage);
            } catch (MessagingException ex) {
                ex.printStackTrace();
            }
        }
    }


    @Override
    /**
     * Obtener los roles del token
     */
    public Collection<? extends GrantedAuthority> getRoles(String token) throws IOException {
        Object roles = getClaims(token).get("rolees");
        Collection<? extends GrantedAuthority> rolees = Arrays.asList(new ObjectMapper()
                .addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityMixin.class)
                .readValue(roles.toString().getBytes(), SimpleGrantedAuthority[].class));
        return rolees;
    }

    @Override
    /**
     * Obtener el token
     */
    public String resolve(String token) {
        if (token != null && token.startsWith(TOKEN_PREFIX)) {
            return token.replace(TOKEN_PREFIX, "");
        }
        return null;
    }
}
