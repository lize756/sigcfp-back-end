package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Faculty;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Language;

import java.util.List;

public interface ILanguageService {

    Language addLanguage(Language language);

    Language updateLanguage(Language language);

    Language searchLanguage(long languId);

    Language deleteLanguage(long languId);

    List<Language> languages();
}
