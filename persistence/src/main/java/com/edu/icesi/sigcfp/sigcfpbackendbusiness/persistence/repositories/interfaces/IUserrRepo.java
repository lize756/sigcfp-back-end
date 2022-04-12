package com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Userr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserrRepo extends JpaRepository<Userr, Long> {
    Userr findUserrByUserEmail(String userEmail);
    Userr findUserrByUserName(String userName);
}
