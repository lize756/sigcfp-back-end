package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.auth.util.FileUtility;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.CurriculumPdf;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.ICurriculumPdfService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces.ICurriculumPdfController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/api/curriculumPdfs")
@CrossOrigin(origins = "*")
//@PreAuthorize("")
public class CurriculumPdfController implements ICurriculumPdfController {

	private ICurriculumPdfService iCurriculumPdfService;

	@Autowired
	public CurriculumPdfController(ICurriculumPdfService iCurriculumPdfService) {
		this.iCurriculumPdfService = iCurriculumPdfService;
	}

	@Override
	@PostMapping("/add")
	public ResponseEntity<CurriculumPdf> addCurriculumPdf(@RequestParam("file") MultipartFile multipartFile) {
		try {
			CurriculumPdf _curriculumPdf = new CurriculumPdf();
			if (!multipartFile.isEmpty()) {
				// String route = "/empleos/img-vacantes/"; // Linux/MAC
				String route = "c:/empleos/img-vacantes/"; // Windows
				String fileName = FileUtility.saveFile(multipartFile, route);
				if (fileName != null) { // The file was upload
					// Save curriculum information
					_curriculumPdf.setCuPdfName(fileName);
					iCurriculumPdfService.addCurriculumPdf(_curriculumPdf);
				}
			}

			return new ResponseEntity<CurriculumPdf>(_curriculumPdf, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@PutMapping("/update/{cuPdfId}")
	public ResponseEntity<CurriculumPdf> updateCurriculumPdf(@PathVariable("cuPdfId") long cuPdfId,
			@RequestBody CurriculumPdf curriculumPdf) {
		Optional<CurriculumPdf> optionalCurriculumPdf = Optional.of(iCurriculumPdfService.searchCurriculumPdf(cuPdfId));
		if (optionalCurriculumPdf.isPresent()) {
			CurriculumPdf _curriculumPdf = optionalCurriculumPdf.get();
			return new ResponseEntity<>(iCurriculumPdfService.updateCurriculumPdf(_curriculumPdf), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	@GetMapping("/{cuPdfId}")
	public ResponseEntity<CurriculumPdf> getCurriculumPdf(@PathVariable("cuPdfId") long cuPdfId) {
		Optional<CurriculumPdf> optionalCurriculumPdf = Optional.of(iCurriculumPdfService.searchCurriculumPdf(cuPdfId));
		if (optionalCurriculumPdf.isPresent()) {
			return new ResponseEntity<>(optionalCurriculumPdf.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	@DeleteMapping("/{cuPdfId}")
	public ResponseEntity<HttpStatus> deleteCurriculumPdf(@PathVariable("cuPdfId") long cuPdfId) {
		try {
			iCurriculumPdfService.deleteCurriculumPdf(cuPdfId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<List<CurriculumPdf>> getCurriculumPdfs() {
		try {
			List<CurriculumPdf> curriculumPdfList = iCurriculumPdfService.CurriculumPdfs();
			if (curriculumPdfList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(curriculumPdfList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@PostMapping("/addFile")
	public ResponseEntity<String> uploadFile(MultipartFile multipartFile) {
		// TODO Auto-generated method stub
		return null;
	}
}
