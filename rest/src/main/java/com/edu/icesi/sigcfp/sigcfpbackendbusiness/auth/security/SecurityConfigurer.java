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
                .antMatchers(HttpMethod.POST,"/api/careers/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString()) // Carreras
                .antMatchers(HttpMethod.GET,"/api/careers/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString(),TypesOfRolees.ROLEE_COMPANY.toString()) // Carreras
                .antMatchers(HttpMethod.PUT, "/api/careers/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString()) // Carreras
                .antMatchers(HttpMethod.DELETE,"/api/careers/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString()) // Carreras

                // Accesos a las rutas de los estudios
                .antMatchers(HttpMethod.POST,"/api/academicStudies/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString()) // Estudios
                .antMatchers(HttpMethod.GET,"/api/academicStudies/**").permitAll() // Estudios
                .antMatchers(HttpMethod.PUT,"/api/academicStudies/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString()) // Estudios
                .antMatchers(HttpMethod.DELETE,"/api/academicStudies/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString()) // Estudios

                // Accesos a las rutas de las ciudades
                .antMatchers(HttpMethod.POST,"/api/cities/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString()) // Ciudades
                .antMatchers(HttpMethod.GET,"/api/cities/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString()) // Ciudades
                .antMatchers(HttpMethod.PUT,"/api/cities/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString()) // Ciudades
                .antMatchers(HttpMethod.DELETE,"/api/cities/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString()) // Ciudades

                // Accesos a las rutas de las compañías
                .antMatchers(HttpMethod.POST,"/api/companies/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString(),"ROLEE_COMPANY") // Empresas
                .antMatchers(HttpMethod.GET,"/api/companies/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString(),TypesOfRolees.ROLEE_PROMOTION_COORDINATOR.toString()) // Empresas
                .antMatchers(HttpMethod.PUT,"/api/companies/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString(),"ROLEE_COMPANY") // Empresas
                .antMatchers(HttpMethod.DELETE,"/api/companies/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString(), "ROLEE_COMPANY") // Empresas

                // Accesos a las rutas de los contactos
                .antMatchers(HttpMethod.POST,"/api/contacts/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString()) // Contactos
                .antMatchers(HttpMethod.GET,"/api/contacts/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString()) // Contactos
                .antMatchers(HttpMethod.PUT,"/api/contacts/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString()) // Contactos
                .antMatchers(HttpMethod.DELETE,"/api/contacts/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString()) // Contactos

                // Accesos a las rutas de los curriculums
                .antMatchers(HttpMethod.POST, "/api/curriculums/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString(),"ROLEE_GRADUATE") // Curriculums
                .antMatchers(HttpMethod.GET, "/api/curriculums/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString(),"ROLEE_GRADUATE") // Curriculums
                .antMatchers(HttpMethod.PUT, "/api/curriculums/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString(),"ROLEE_GRADUATE") // Curriculums
                .antMatchers(HttpMethod.DELETE, "/api/curriculums/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString(), "ROLEE_GRADUATE") // Curriculums

                // Accesos a las rutas de los PDFs de los curriculums
                .antMatchers(HttpMethod.POST,"/api/curriculumPdfs/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString(), "ROLEE_GRADUATE") // PDF de curriculums
                .antMatchers(HttpMethod.GET,"/api/curriculumPdfs/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString(),"ROLEE_GRADUATE") // PDF de curriculums
                .antMatchers(HttpMethod.PUT,"/api/curriculumPdfs/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString(),"ROLEE_GRADUATE") // PDF de curriculums
                .antMatchers(HttpMethod.DELETE,"/api/curriculumPdfs/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString(),"ROLEE_GRADUATE") // PDF de curriculums

                // Accesos a las rutas de los grupos étnicos
                .antMatchers(HttpMethod.POST,"/api/ethnicGroups/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString()) // Grupos étnicos
                .antMatchers(HttpMethod.GET,"/api/ethnicGroups/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString()) // Grupos étnicos
                .antMatchers(HttpMethod.PUT,"/api/ethnicGroups/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString()) // Grupos étnicos
                .antMatchers(HttpMethod.DELETE,"/api/ethnicGroups/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString()) // Grupos étnicos

                // Accesos a las rutas de las facultades
                .antMatchers(HttpMethod.POST, "/api/faculties/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString()) // Facultades
                .antMatchers(HttpMethod.GET, "/api/faculties/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString()) // Facultades
                .antMatchers(HttpMethod.PUT, "/api/faculties/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString()) // Facultades
                .antMatchers(HttpMethod.DELETE, "/api/faculties/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString()) // Facultades

                // Accesos a las rutas de las solicitudes de practicantes
                .antMatchers(HttpMethod.POST, "/api/internRequests/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString(),TypesOfRolees.ROLEE_COMPANY.toString()) // Solicitudes de practicante
                .antMatchers(HttpMethod.GET, "/api/internRequests/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString(),TypesOfRolees.ROLEE_COMPANY.toString()) // Solicitudes de practicante
                .antMatchers(HttpMethod.PUT, "/api/internRequests/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString(),TypesOfRolees.ROLEE_COMPANY.toString()) // Solicitudes de practicante
                .antMatchers(HttpMethod.DELETE, "/api/internRequests/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString(),"ROLEE_COMPANY") // Solicitudes de practicante

                // Accesos a las rutas de los lenguajes
                .antMatchers(HttpMethod.POST,"/api/languages/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString()) // Lenguajes
                .antMatchers(HttpMethod.GET,"/api/languages/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString()) // Lenguajes
                .antMatchers(HttpMethod.PUT,"/api/languages/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString()) // Lenguajes
                .antMatchers(HttpMethod.DELETE,"/api/languages/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString()) // Lenguajes

                // Accesos a las rutas de las personas
                .antMatchers(HttpMethod.POST,"/api/persons/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString()) // Personas
                .antMatchers(HttpMethod.GET,"/api/persons/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString(),TypesOfRolees.ROLEE_PROMOTION_COORDINATOR.toString()) // Personas
                .antMatchers(HttpMethod.PUT,"/api/persons/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString()) // Personas
                .antMatchers(HttpMethod.DELETE,"/api/persons/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString()) // Personas

                // Accesos a las rutas de los roles
                .antMatchers(HttpMethod.POST,"/api/rolees/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString()) // Roles
                .antMatchers(HttpMethod.GET,"/api/rolees/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString()) // Roles
                .antMatchers(HttpMethod.PUT,"/api/rolees/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString()) // Roles
                .antMatchers(HttpMethod.DELETE,"/api/rolees/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString()) // Roles

                // Accesos a las rutas de los usuarios
                .antMatchers(HttpMethod.POST,"/api/userrs/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString(),"ROLEE_PROMOTION_COORDINATOR") // Usuarios
                .antMatchers(HttpMethod.GET,"/api/userrs/**").permitAll() // Usuarios
                .antMatchers(HttpMethod.PUT,"/api/userrs/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString()) // Usuarios
                .antMatchers(HttpMethod.DELETE,"/api/userrs/**").hasAnyAuthority(TypesOfRolees.ROLEE_LOCATION_COORDINATOR.toString()) // Usuarios

                // Accesos a las rutas de las notificaciones
                .antMatchers(HttpMethod.POST,"/api/emailNotifications/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR", "ROLEE_DIRECTOR") // Notificaciones por correo
                .antMatchers(HttpMethod.GET,"/api/emailNotifications/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR", "ROLEE_DIRECTOR")// Notificaciones por correo
                .antMatchers(HttpMethod.PUT,"/api/emailNotifications/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR", "ROLEE_DIRECTOR") // Notificaciones por correo
                .antMatchers(HttpMethod.DELETE,"/api/emailNotifications/**").hasAnyAuthority("ROLEE_LOCATION_COORDINATOR", "ROLEE_DIRECTOR") // Notificaciones por correo

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
