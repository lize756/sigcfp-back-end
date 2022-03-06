package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Career;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.ICareerService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces.ICareerController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/career")
@CrossOrigin(origins = "*")
//@PreAuthorize("")
public class CareerController implements ICareerController {

	ICareerService iCareerService;

	@Autowired
	public CareerController(ICareerService iCareerService) {
		this.iCareerService = iCareerService;
	}

	@Override
	@PostMapping("/add")
	public ResponseEntity<Career> addCareer(@RequestBody Career career) {
		try {
			Career _career = iCareerService.addCareer(career);
			return new ResponseEntity<Career>(_career, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@PutMapping("/update/{careId}")
	public ResponseEntity<Career> updateCareer(@PathVariable long careId) {
		Optional<Career> carreOptional = Optional.of(iCareerService.searchCareer(careId));
		if (carreOptional.isPresent()) {
			Career _career = carreOptional.get();
			return new ResponseEntity<>(iCareerService.updateCareer(_career), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	@GetMapping("/{careId}")
	public ResponseEntity<Career> getCareer(@PathVariable long careId) {
		Optional<Career> carreOptional = Optional.of(iCareerService.searchCareer(careId));
		if (carreOptional.isPresent()) {
			return new ResponseEntity<>(carreOptional.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	@DeleteMapping("/{careId}")
	public ResponseEntity<HttpStatus> deleteCareer(@PathVariable long careId) {
		try {
			iCareerService.deleteCareer(careId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@GetMapping("/careers")
	public ResponseEntity<List<Career>> getCareers() {
		try {
			List<Career> careers = iCareerService.careers();
			if (careers.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(careers, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
