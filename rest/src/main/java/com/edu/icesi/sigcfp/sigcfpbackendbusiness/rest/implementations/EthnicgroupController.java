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

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Ethnicgroup;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.IEthnicgroupService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces.IEthnicgroupController;

@RestController()
@RequestMapping("/ethnicGroups")
@CrossOrigin(origins = "*")
//@PreAuthorize("")
public class EthnicgroupController implements IEthnicgroupController {

	private IEthnicgroupService iEthnicgroupService;

	@Autowired
	public EthnicgroupController(IEthnicgroupService iEthnicgroupService) {
		this.iEthnicgroupService = iEthnicgroupService;
	}

	@Override
	@PostMapping("/add")
	public ResponseEntity<Ethnicgroup> addEthnicgroup(@RequestBody Ethnicgroup ethnicgroup) {
		try {
			Ethnicgroup _eEthnicgroup = iEthnicgroupService.addEthnicgroup(ethnicgroup);
			return new ResponseEntity<Ethnicgroup>(_eEthnicgroup, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@PutMapping("/update/{etgrId}")
	public ResponseEntity<Ethnicgroup> updateEthnicgroup(@PathVariable("etgrId") long etgrId, @RequestBody Ethnicgroup ethnicgroup) {
		Optional<Ethnicgroup> ethnicGrouptOpt = Optional.of(iEthnicgroupService.searchEthnicgroup(etgrId));
		if (ethnicGrouptOpt.isPresent()) {
			return new ResponseEntity<>(iEthnicgroupService.updateEthnicgroup(ethnicgroup), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	@GetMapping("/{etgrId}")
	public ResponseEntity<Ethnicgroup> getEthnicgroup(@PathVariable("etgrId") long etgrId) {
		Optional<Ethnicgroup> ethnicgroupOpt = Optional.of(iEthnicgroupService.searchEthnicgroup(etgrId));
		if (ethnicgroupOpt.isPresent()) {
			return new ResponseEntity<>(ethnicgroupOpt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	@DeleteMapping("/{etgrId}")
	public ResponseEntity<HttpStatus> deleteEthnicgroup(@PathVariable("etgrId") long etgrId) {
		try {
			iEthnicgroupService.deleteEthnicgroup(etgrId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@GetMapping()
	public ResponseEntity<List<Ethnicgroup>> getEthnicgroups() {
		try {
			List<Ethnicgroup> ethnicgroups = iEthnicgroupService.ethnicgroups();
			if (ethnicgroups.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(ethnicgroups, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
