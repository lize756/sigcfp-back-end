package com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPersonRepo extends JpaRepository<Person, Long> {
    @Override
    List<Person> findAll();
    
    @Query("select p from Person p where p.persId = ?1")
    Person getPersonById(long persId);
    
    
}
