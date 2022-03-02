package com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Ethnicgroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IEthnicgroup extends JpaRepository<Ethnicgroup,Long> {
    @Override
    List<Ethnicgroup> findAll();
}
