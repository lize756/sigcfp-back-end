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

                // Accesos a las rutas de las carreras
                .antMatchers(HttpMethod.POST,"/api/careers/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Carreras
                .antMatchers(HttpMethod.GET,"/api/careers/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Carreras
                .antMatchers(HttpMethod.PUT, "/api/careers/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Carreras
                .antMatchers(HttpMethod.DELETE,"/api/careers/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Carreras

                // Accesos a las rutas de los estudios
                .antMatchers(HttpMethod.POST,"/api/academicStudies/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Estudios
                .antMatchers(HttpMethod.GET,"/api/academicStudies/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Estudios
                .antMatchers(HttpMethod.PUT,"/api/academicStudies/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Estudios
                .antMatchers(HttpMethod.DELETE,"/api/academicStudies/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Estudios

                // Accesos a las rutas de las ciudades
                .antMatchers(HttpMethod.POST,"/api/cities/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Ciudades
                .antMatchers(HttpMethod.GET,"/api/cities/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Ciudades
                .antMatchers(HttpMethod.PUT,"/api/cities/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Ciudades
                .antMatchers(HttpMethod.DELETE,"/api/cities/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Ciudades

                // Accesos a las rutas de las compañías
                .antMatchers(HttpMethod.POST,"/api/companies/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Empresas
                .antMatchers(HttpMethod.GET,"/api/companies/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Empresas
                .antMatchers(HttpMethod.PUT,"/api/companies/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Empresas
                .antMatchers(HttpMethod.DELETE,"/api/companies/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Empresas

                // Accesos a las rutas de los contactos
                .antMatchers(HttpMethod.POST,"/api/contacts/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Contactos
                .antMatchers(HttpMethod.GET,"/api/contacts/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Contactos
                .antMatchers(HttpMethod.PUT,"/api/contacts/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Contactos
                .antMatchers(HttpMethod.DELETE,"/api/contacts/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Contactos

                // Accesos a las rutas de los curriculums
                .antMatchers(HttpMethod.POST, "/api/curriculums/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR","ROLEE_GRADUATE") // Curriculums
                .antMatchers(HttpMethod.GET, "/api/curriculums/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR","ROLEE_GRADUATE") // Curriculums
                .antMatchers(HttpMethod.PUT, "/api/curriculums/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR","ROLEE_GRADUATE") // Curriculums
                .antMatchers(HttpMethod.DELETE, "/api/curriculums/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR", "ROLEE_GRADUATE") // Curriculums

                // Accesos a las rutas de los PDFs de los curriculums
                .antMatchers(HttpMethod.POST,"/api/curriculumPdfs/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR", "ROLEE_GRADUATE") // PDF de curriculums
                .antMatchers(HttpMethod.GET,"/api/curriculumPdfs/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR","ROLEE_GRADUATE") // PDF de curriculums
                .antMatchers(HttpMethod.PUT,"/api/curriculumPdfs/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR","ROLEE_GRADUATE") // PDF de curriculums
                .antMatchers(HttpMethod.DELETE,"/api/curriculumPdfs/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR","ROLEE_GRADUATE") // PDF de curriculums

                // Accesos a las rutas de los grupos étnicos
                .antMatchers(HttpMethod.POST,"/api/ethnicGroups/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Grupos étnicos
                .antMatchers(HttpMethod.GET,"/api/ethnicGroups/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Grupos étnicos
                .antMatchers(HttpMethod.PUT,"/api/ethnicGroups/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Grupos étnicos
                .antMatchers(HttpMethod.DELETE,"/api/ethnicGroups/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Grupos étnicos

                // Accesos a las rutas de las facultades
                .antMatchers(HttpMethod.POST, "/api/faculties/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Facultades
                .antMatchers(HttpMethod.GET, "/api/faculties/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Facultades
                .antMatchers(HttpMethod.PUT, "/api/faculties/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Facultades
                .antMatchers(HttpMethod.DELETE, "/api/faculties/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Facultades

                // Accesos a las rutas de las solicitudes de practicantes
                .antMatchers(HttpMethod.POST, "/api/internRequests/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR","ROLEE_COMPANY") // Solicitudes de practicante
                .antMatchers(HttpMethod.GET, "/api/internRequests/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR","ROLEE_COMPANY") // Solicitudes de practicante
                .antMatchers(HttpMethod.PUT, "/api/internRequests/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR","ROLEE_COMPANY") // Solicitudes de practicante
                .antMatchers(HttpMethod.DELETE, "/api/internRequests/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR","ROLEE_COMPANY") // Solicitudes de practicante

                // Accesos a las rutas de los lenguajes
                .antMatchers(HttpMethod.POST,"/api/languages/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Lenguajes
                .antMatchers(HttpMethod.GET,"/api/languages/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Lenguajes
                .antMatchers(HttpMethod.PUT,"/api/languages/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Lenguajes
                .antMatchers(HttpMethod.DELETE,"/api/languages/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Lenguajes

                // Accesos a las rutas de las personas
                .antMatchers(HttpMethod.POST,"/api/persons/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Personas
                .antMatchers(HttpMethod.GET,"/api/persons/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Personas
                .antMatchers(HttpMethod.PUT,"/api/persons/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Personas
                .antMatchers(HttpMethod.DELETE,"/api/persons/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Personas

                // Accesos a las rutas de los roles
                .antMatchers(HttpMethod.POST,"/api/rolees/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Roles
                .antMatchers(HttpMethod.GET,"/api/rolees/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Roles
                .antMatchers(HttpMethod.PUT,"/api/rolees/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Roles
                .antMatchers(HttpMethod.DELETE,"/api/rolees/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Roles

                // Accesos a las rutas de los usuarios
                .antMatchers(HttpMethod.POST,"/api/userrs/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Usuarios
                .antMatchers(HttpMethod.GET,"/api/userrs/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Usuarios
                .antMatchers(HttpMethod.PUT,"/api/userrs/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Usuarios
                .antMatchers(HttpMethod.DELETE,"/api/userrs/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Usuarios

                // Accesos a las rutas de las notificaciones
                .antMatchers(HttpMethod.POST,"/api/emailNotifications/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Notificaciones por correo
                .antMatchers(HttpMethod.GET,"/api/emailNotifications/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR")// Notificaciones por correo
                .antMatchers(HttpMethod.PUT,"/api/emailNotifications/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Notificaciones por correo
                .antMatchers(HttpMethod.DELETE,"/api/emailNotifications/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR") // Notificaciones por correo

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
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.setAllowCredentials(true);
        //config.addAllowedOrigin("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }




}
