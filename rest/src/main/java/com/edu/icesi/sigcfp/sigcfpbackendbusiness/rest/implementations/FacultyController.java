package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.implementations;

import java.util.List;
import java.util.Optional;

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

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Faculty;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.IFacultyService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces.IFacultyController;

@RestController()
@RequestMapping("/faculty")
@CrossOrigin(origins = "*")
//@PreAuthorize("")
public class FacultyController implements IFacultyController {

	private IFacultyService iFacultyService;

	public FacultyController(IFacultyService iFacultyService) {
		this.iFacultyService = iFacultyService;
	}

	@Override
	@PostMapping("/add")
	public ResponseEntity<Faculty> addFaculty(@RequestBody Faculty faculty) {
		try {
			Faculty _faculty = iFacultyService.addFaculty(faculty);
			return new ResponseEntity<Faculty>(_faculty, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@PutMapping("/update/{facuId}")
	public ResponseEntity<Faculty> updateFaculty(@PathVariable("facuId") long facuId,@RequestBody Faculty faculty) {
		Optional<Faculty> facultyOpt = Optional.of(iFacultyService.searchFaculty(facuId));
		if (facultyOpt.isPresent()) {
			return new ResponseEntity<>(iFacultyService.updateFaculty(faculty), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	@GetMapping("/{facuId}")
	public ResponseEntity<Faculty> getFaculty(@PathVariable("facuId") long facuId) {
		Optional<Faculty> facultyOpt = Optional.of(iFacultyService.searchFaculty(facuId));
		if (facultyOpt.isPresent()) {
			return new ResponseEntity<>(facultyOpt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	@DeleteMapping("/{facuId}")
	public ResponseEntity<HttpStatus> deleteContact(@PathVariable("facuId") long facuId) {
		try {
			iFacultyService.deleteFaculty(facuId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@GetMapping("/faculties")
	public ResponseEntity<List<Faculty>> getFaculties() {
		try {
			List<Faculty> faculties = iFacultyService.faculties();
			if (faculties.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(faculties, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
