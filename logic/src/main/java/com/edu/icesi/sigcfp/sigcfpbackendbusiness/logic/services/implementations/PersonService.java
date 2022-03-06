package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Person;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.IPersonService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.IPersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PersonService implements IPersonService {

	IPersonRepo iPersonRepo;

	@Autowired
	public PersonService(IPersonRepo iPersonRepo) {
		this.iPersonRepo = iPersonRepo;
	}

	@Override
	@Transactional
	public Person addPerson(Person person) {
		if (!iPersonRepo.existsById(person.getPersId())) {
			return iPersonRepo.save(person);
		} else {
			return null;
		}
	}

	@Override
	@Transactional
	public Person updatePerson(Person person) {
		return iPersonRepo.save(person);
	}

	@Override
	@Transactional
	public Person searchPerson(long persId) {
		if (iPersonRepo.existsById(persId)) {
			return iPersonRepo.getById(persId);
		} else {
			return null;
		}
	}

	@Override
	@Transactional
	public Person deletePerson(long persId) {
		Person personToDelete = null;
		if (iPersonRepo.existsById(persId)) {
			personToDelete = iPersonRepo.findById(persId).get();
			iPersonRepo.delete(iPersonRepo.getById(persId));
		} else {
			return null;
		}
		return personToDelete;
	}

	@Override
	@Transactional
	public List<Person> persons() {
		return iPersonRepo.findAll();
	}
}
