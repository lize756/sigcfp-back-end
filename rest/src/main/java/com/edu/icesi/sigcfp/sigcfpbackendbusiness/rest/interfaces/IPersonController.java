package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Person;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPersonController {

    ResponseEntity<String> addPerson(Person person);

    ResponseEntity<String> updatePerson(Person person, long persId);

    ResponseEntity<String> getPerson(long persId);

    ResponseEntity<String> deletePerson(long persId);

    ResponseEntity<List<Person>> getPersons();

}
