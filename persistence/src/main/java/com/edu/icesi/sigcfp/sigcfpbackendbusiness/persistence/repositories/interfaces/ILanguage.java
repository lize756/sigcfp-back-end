package com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ILanguage extends JpaRepository<Language,Long> {

    List<Language> findAll();
}
