package com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface IContactRepo extends JpaRepository<Contact, Long> {
    @Override
    List<Contact> findAll();
    
    
    /**
     * Allow search the contacts associated with a companies
     * @param id
     * @return
     */
    @Query("select c from Contact c where c.company.compId = ?1")
    List<Contact> findContactsByCompany(long compId);
}
