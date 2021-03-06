package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Person;

import java.util.List;


public interface IPersonService {

    Person addPerson(Person person);

    Person updatePerson(Person person);

    Person searchPerson(long persId);

    Person deletePerson(long persId);

    List<Person> persons();

}
