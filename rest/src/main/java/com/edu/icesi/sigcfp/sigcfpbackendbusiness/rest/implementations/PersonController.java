package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Person;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.IPersonService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces.IPersonController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController()
@RequestMapping("/api/persons")
@CrossOrigin(origins = "*")
//@PreAuthorize("")
public class PersonController implements IPersonController {

    private IPersonService iPersonService;

    @Autowired
    public PersonController(IPersonService iPersonService) {
        this.iPersonService = iPersonService;
    }

    @Override
    @PostMapping("/add")
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        try {
            Person _person = iPersonService.addPerson(person);
            return new ResponseEntity<Person>(_person, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	@Override
	@PutMapping("/update/{persId}")
	public ResponseEntity<Person> updatePerson(@PathVariable("persId") long persId,@RequestBody Person person) {
		try {
			
		Optional<Person> personOptional = Optional.of(iPersonService.searchPerson(persId));
		System.out.println("ENTRE----------------->>>>>>>>> "+personOptional);
			if (personOptional.isPresent()) {
				return new ResponseEntity<>(iPersonService.updatePerson(person), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	@PutMapping("/partiallyUpdate/{persId}")
	public ResponseEntity<Person> partiallyUpdatePerson(@PathVariable("persId") long persId,@RequestBody Person person) {
		try {
			
		Optional<Person> personOptional = Optional.of(iPersonService.searchPerson(persId));
		System.out.println("ENTRE----------------->>>>>>>>> "+personOptional);
			if (personOptional.isPresent()) {
				Person previousPerson = personOptional.get();
				//Elements to partially update
				person.setLanguages(previousPerson.getLanguages());
				person.setCurriculum(previousPerson.getCurriculum());
				person.setCareers(previousPerson.getCareers());
				person.setEthnicgroups(previousPerson.getEthnicgroups());
				return new ResponseEntity<>(iPersonService.updatePerson(person), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

    }

    @Override
    @GetMapping("/{persId}")
    public ResponseEntity<Person> getPerson(@PathVariable("persId") long persId) {
        Optional<Person> personOptional = Optional.of(iPersonService.searchPerson(persId));
        if (personOptional.isPresent()) {
            return new ResponseEntity<>(personOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @DeleteMapping("/{persId}")
    public ResponseEntity<HttpStatus> deletePerson(@PathVariable("persId") long persId) {
        try {
            iPersonService.deletePerson(persId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping()
    public ResponseEntity<List<Person>> getPersons() {
        try {
            List<Person> persons = iPersonService.persons();
            if (persons.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(persons, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
