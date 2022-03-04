package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.implementations;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Academicstudy;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.IAcademicstudyService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces.IAcademicstudyController;

@RestController()
@RequestMapping("/academicStudy")
@CrossOrigin(origins = "*")
//@PreAuthorize("")
public class AcademicStudyController implements IAcademicstudyController {

	IAcademicstudyService iAcademicstudyService;

	@Autowired
	public AcademicStudyController(IAcademicstudyService iAcademicstudyService) {
		this.iAcademicstudyService = iAcademicstudyService;
	}

	@Override
	@PostMapping("/add")
	public ResponseEntity<Academicstudy> addAcademicstudy(@RequestBody Academicstudy academicstudy) {
		try {
			Academicstudy _acAcademicstudy = iAcademicstudyService.addAcademicstudy(academicstudy);
			return new ResponseEntity<Academicstudy>(_acAcademicstudy, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@PutMapping("/update/{acadStudId}")
	public ResponseEntity<Academicstudy> updateAcademicstudy(@PathVariable("acadStudId") long acadStudId,
			@RequestBody Academicstudy academicstudy) {
		Optional<Academicstudy> academicstudyOpt = Optional.of(iAcademicstudyService.searchAcademicstudy(acadStudId));
		if (academicstudyOpt.isPresent()) {
			Academicstudy _academicstudy = academicstudyOpt.get();
			return new ResponseEntity<>(iAcademicstudyService.updateAcademicstudy(_academicstudy), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	@GetMapping("/{acadStudId}")
	public ResponseEntity<Academicstudy> getAcademicstudy(@PathVariable("acadStudId") long acadStudId) {

		Optional<Academicstudy> academicstudyOpt = Optional.of(iAcademicstudyService.searchAcademicstudy(acadStudId));
		if (academicstudyOpt.isPresent()) {
			return new ResponseEntity<>(academicstudyOpt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	@DeleteMapping("/{acadStudId}")
	public ResponseEntity<Academicstudy> deleteAcademicstudy(@PathVariable("acadStudId") long acadStudId) {
		try {
			iAcademicstudyService.deleteAcademicstudy(acadStudId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@GetMapping("/academicStudies")
	public ResponseEntity<List<Academicstudy>> getAcademicstudies() {
		try {
			List<Academicstudy> academicstudies = iAcademicstudyService.academicstudies();
			if (academicstudies.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(academicstudies, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
