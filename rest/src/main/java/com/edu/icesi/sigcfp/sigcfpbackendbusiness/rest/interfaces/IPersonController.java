package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IPersonController {
    /**
     * Allow add new person in the system.
     *
     * @param person person to add.
     * @return a responseEntity that represent the whole HTTP response: status code,
     * headers, and body.
     */
    ResponseEntity<Person> addPerson(Person person);

    /**
     * Allow update a person.
     *
     * @param persId id of person to update.
     * @return a responseEntity that represent the whole HTTP response: status code,
     * headers, and body.
     */
    ResponseEntity<Person> updatePerson(long persId, Person person);

    /**
     * Allow partially update a person.
     * @param persId persId id of person to update.
     * @param person person to update
     * @return
     */
	ResponseEntity<Person> partiallyUpdatePerson(long persId,Person person);
	
    /**
	 * Allows to obtain a person through you id.
	 * 
	 * @param persId id to search one person
	 * @return a responseEntity that represent the whole HTTP response: status code,
	 *         headers, and body.
	 */
    ResponseEntity<Person> getPerson(long persId);

    /**
     * Allows delete a person through you id
     *
     * @param persId id of the person that you want to delete
     * @return a responseEntity that represent the whole HTTP response: status code,
     * headers, and body.
     */
    ResponseEntity<HttpStatus> deletePerson(long persId);

    /**
     * Allows to obtain the list of persons saved in the database.
     *
     * @return a responseEntity that represent the whole HTTP response: status code,
     * headers, and body.
     */
    ResponseEntity<List<Person>> getPersons();
    
   


}
