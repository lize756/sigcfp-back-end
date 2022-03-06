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

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Language;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.ILanguageService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces.ILanguageController;


@RestController()
@RequestMapping("/language")
@CrossOrigin(origins = "*")
//@PreAuthorize("")
public class LanguageController implements ILanguageController {

	private ILanguageService ilanguageService;

	@Autowired
	public LanguageController(ILanguageService ilanguageService) {
		this.ilanguageService = ilanguageService;
	}

	@Override
	@PostMapping("/add")
	public ResponseEntity<Language> addLanguage(@RequestBody Language language) {
		try {
			Language _language = ilanguageService.addLanguage(language);
			return new ResponseEntity<Language>(_language, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@PutMapping("/update/{languId}")
	public ResponseEntity<Language> updateLanguage(@PathVariable("languId") long languId,@RequestBody Language language) {
		Optional<Language> languageOptional = Optional.of(ilanguageService.searchLanguage(languId));
		if (languageOptional.isPresent()) {
			return new ResponseEntity<>(ilanguageService.updateLanguage(language), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	@GetMapping("/{languId}")
	public ResponseEntity<Language> getLanguage(@PathVariable("languId") long languId) {
		Optional<Language> languageOptional = Optional.of(ilanguageService.searchLanguage(languId));

		if (languageOptional.isPresent()) {
			return new ResponseEntity<>(languageOptional.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@Override
	@DeleteMapping("/{languId}")
	public ResponseEntity<HttpStatus> deleteLanguage(@PathVariable("languId") long languId) {
		try {
			ilanguageService.deleteLanguage(languId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@GetMapping("/languages")
	public ResponseEntity<List<Language>> getLanguages() {
		try {
			List<Language> languages = ilanguageService.languages();
			if (languages.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(languages, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
