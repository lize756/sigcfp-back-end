package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPersonService {

    Person addPerson(Person person);

    Person updatePerson(Person person);

    Person searchPerson(long persId);

    Person deletePerson(long persId);

    List<Person> persons();

}
