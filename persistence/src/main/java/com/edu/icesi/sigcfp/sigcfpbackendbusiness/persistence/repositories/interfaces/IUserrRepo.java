package com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Company;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Person;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Userr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface IUserrRepo extends JpaRepository<Userr, Long> {
    @Query("select u from Userr u where u.userName = ?1")
    Userr findUserrByUserName(String userName);
    @Query("select u.company from Userr u where u.userName = ?1")
    Company findCompanyByUserName(String userName);
    @Query("select u.person from Userr u where u.userName = ?1")
    Person findPersonByUserName(String userName);
}
