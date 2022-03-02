package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Language;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.ILanguageService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.ILanguageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService implements ILanguageService {

    ILanguageRepo iLanguageRepo;

    @Autowired
    public LanguageService(ILanguageRepo iLanguageRepo) {
        this.iLanguageRepo = iLanguageRepo;
    }

    @Override
    public Language addLanguage(Language language) {
        return null;
    }

    @Override
    public Language updateLanguage(Language language) {
        return null;
    }

    @Override
    public Language searchLanguage(long languId) {
        return null;
    }

    @Override
    public Language deleteLanguage(long languId) {
        return null;
    }

    @Override
    public List<Language> languages() {
        return null;
    }
}
