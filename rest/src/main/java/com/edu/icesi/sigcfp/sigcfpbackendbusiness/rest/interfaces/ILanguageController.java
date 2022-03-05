package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Language;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ILanguageController {

    ResponseEntity<Language> addLanguage(Language language);

    ResponseEntity<Language> updateLanguage(long languId, Language language);

    ResponseEntity<Language> getLanguage(long languId);

    ResponseEntity<Language> deleteLanguage(long languId);

    ResponseEntity<List<Language>> getLanguages();
}
