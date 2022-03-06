package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Language;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ILanguageController {
	/**
	 * Allow add new language in the system.
	 * 
	 * @param language to add.
	 * @return a responseEntity that represent the whole HTTP response: status code,
	 *         headers, and body.
	 */
    ResponseEntity<Language> addLanguage(Language language);
    /**
   	 * Allow update a language.
   	 * 
   	 * @param languId id of the language to update.
   	 * @return a responseEntity that represent the whole HTTP response: status code,
   	 *         headers, and body.
   	 */
    ResponseEntity<Language> updateLanguage( long languId);

    /**
   	 * Allows to obtain a language for your id.
   	 * 
   	 * @param languId id to search one language
   	 * @return a responseEntity that represent the whole HTTP response: status code,
   	 *         headers, and body.
   	 */
    ResponseEntity<Language> getLanguage(long languId);

    /**
	 * Allows delete a language through you id
	 * 
	 * @param languId id of the language that you want to delete
	 * @return a responseEntity that represent the whole HTTP response: status code,
	 *         headers, and body.
	 */
    ResponseEntity<HttpStatus> deleteLanguage(long languId);

    /**
	 * Allows to obtain the list of languages saved in the database.
	 * 
	 * @return a responseEntity that represent the whole HTTP response: status code,
	 *         headers, and body.
	 */
    ResponseEntity<List<Language>> getLanguages();
}
