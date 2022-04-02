package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Userr;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.IUserrService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces.IUserrController;

@RestController()
@RequestMapping("/userrs")
//@PreAuthorize("")
public class UserrController implements IUserrController{

	private IUserrService iUserrService;

	
	@Autowired
	public UserrController(IUserrService iUserrService) {
		this.iUserrService = iUserrService;
	}
	
	
	@Override
	@PostMapping("/add")
	public ResponseEntity<Userr> addUserr(@RequestBody Userr userr) {
		try {
			Userr _userr = iUserrService.addUserr(userr);
			return new ResponseEntity<Userr>(_userr, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}	}

	@Override
	@PutMapping("/update/{userId}")
	public ResponseEntity<Userr> updateUserr(@PathVariable("userId") long userId,@RequestBody Userr userr) {
		Optional<Userr> userrOptional = Optional.of(iUserrService.searchUserr(userId));
		if (userrOptional.isPresent()) {
			return new ResponseEntity<>(iUserrService.updateUserr(userr), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	@GetMapping("/{userId}")
	public ResponseEntity<Userr> getUserr(@PathVariable("userId") long userId) {
		Optional<Userr> userrOptional = Optional.of(iUserrService.searchUserr(userId));
		if (userrOptional.isPresent()) {
			return new ResponseEntity<>(userrOptional.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	@DeleteMapping("/{userId}")
	public ResponseEntity<HttpStatus> deleteUserr(@PathVariable("userId") long userId) {
		try {
			iUserrService.deleteUserr(userId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@GetMapping()
	public ResponseEntity<List<Userr>> getUserr() {
		try {
			List<Userr> userrs = iUserrService.userrs();
			if (userrs.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(userrs, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
