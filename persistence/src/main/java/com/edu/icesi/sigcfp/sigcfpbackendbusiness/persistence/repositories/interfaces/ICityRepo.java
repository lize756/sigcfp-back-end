package com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ICityRepo extends JpaRepository<City,Long> {
    @Override
    List<City> findAll();
}
