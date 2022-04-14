package com.edu.icesi.sigcfp.sigcfpbackendbusiness.auth.security;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.auth.services.implementations.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
/**
 * Clase que configura la seguridad de la aplicación
 */
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired private UserDetailsService myUserDetailsService; // Servicio que provee los usuarios
    @Autowired private BCryptPasswordEncoder passwordEncoder; // Encriptador de contraseñas
    @Autowired private JWTService jwtService; // Servicio que provee el token


    @Autowired
    /**
     * Método que realiza la configuración global de la seguridad
     */
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    /**
     * Método que configura la seguridad de la aplicación
     */
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable() // Disabling csrf
                .httpBasic().disable() // Disabling http basic
                .cors() // Enabling cors
                .and()

                .authorizeHttpRequests() // Authorizing incoming requests
                // Our public endpoints
                .antMatchers("/api/auth/login").permitAll() // Logueo
                .antMatchers("/api/auth/register").permitAll() // Registro
                // Our private endpoints
                // Accesos a
                .antMatchers(HttpMethod.POST,"/api/careers/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Carreras
                .antMatchers(HttpMethod.GET,"/api/careers/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Carreras
                .antMatchers(HttpMethod.PUT, "/api/careers/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Carreras
                .antMatchers(HttpMethod.DELETE,"/api/careers/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Carreras


                .antMatchers("/api/academicStudies/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Estudios

                .antMatchers("/api/cities/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Ciudades
                .antMatchers("/api/companies/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Empresas
                .antMatchers("/api/contacts/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Contactos
                .antMatchers("/api/curriculums/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Curriculums
                .antMatchers("/api/curriculumPdfs/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // PDF de curriculums
                .antMatchers("/api/ethnicGroups/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Grupos étnicos
                .antMatchers("/api/faculties/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Facultades
                .antMatchers("/api/internRequests/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Solicitudes de practicante
                .antMatchers("/api/languages/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Lenguajes
                .antMatchers("/api/persons/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Personas
                .antMatchers("/api/rolees/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Roles
                .antMatchers("/api/userrs/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Usuarios
                .anyRequest().authenticated() // All other requests need to be authenticated

                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtService)) // Agrega el filtro de autenticación
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtService)) // Agrega el filtro de autorización
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }


    @Bean
    /**
     * Método que configura el encriptador de contraseñas
     */
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Exposing the bean of the authentication manager which will be used to run the authentication process
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    /**
     * Used by spring security if CORS is enabled.
     */
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod(String.valueOf(Arrays.asList("GET", "POST", "PUT", "DELETE")));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
