package com.edu.icesi.sigcfp.sigcfpbackendbusiness.auth.security;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Userr;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.IUserrService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
/**
 * Clase que implementa la interfaz UserDetailsService para obtener los datos de un usuario
 */
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserrService iUserrService; //Servicio de usuarios
    private Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class); //Logger de la clase MyUserDetailsService

    @Override
    @Transactional()
    /**
     * Metodo que obtiene los datos de un usuario
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Userr userr = iUserrService.findUserrByUserName(username);
        if (userr == null) {
            logger.error("Error: no existe el usuario " + username);
            throw new UsernameNotFoundException("El username: " + username + " no existe en el sistema");
        }
        List<GrantedAuthority> rolees = new ArrayList<GrantedAuthority>();
        rolees.add(new SimpleGrantedAuthority(userr.getRolee().getRoleName()));
        if (rolees.isEmpty()) {
            logger.error("Error: el usuario " + username + " no tiene roles asignados");
            throw new UsernameNotFoundException("El usuario: " + username + " no tiene roles asignados");
        }
        return new User(userr.getUserName(), userr.getUserPassword(), rolees);
    }
}
