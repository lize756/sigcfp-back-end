package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Language;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.ILanguageService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.ILanguageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class LanguageService implements ILanguageService {

    ILanguageRepo iLanguageRepo;

    @Autowired
    public LanguageService(ILanguageRepo iLanguageRepo) {
        this.iLanguageRepo = iLanguageRepo;
    }

    @Override
    @Transactional
    public Language addLanguage(Language language) {
        if (!iLanguageRepo.existsById(language.getLanguId())) {
            return iLanguageRepo.save(language);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Language updateLanguage(Language language) {
        return iLanguageRepo.save(language);
    }

    @Override
    @Transactional
    public Language searchLanguage(long languId) {
        if (iLanguageRepo.existsById(languId)) {
            return iLanguageRepo.getById(languId);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Language deleteLanguage(long languId) {
        Language languageToDelete = null;
        if (iLanguageRepo.existsById(languId)) {
            languageToDelete = iLanguageRepo.findById(languId).get();
            iLanguageRepo.delete(iLanguageRepo.getById(languId));
        } else {
            return null;
        }
        return languageToDelete;
    }

    @Override
    @Transactional
    public List<Language> languages() {
        return iLanguageRepo.findAll();
    }

	@Override
	@Transactional
	public List<Language> addLanguages(List<Language> languages) {
		return iLanguageRepo.saveAll(languages);
	}
}
