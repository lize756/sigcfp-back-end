package com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICompanyRepo extends JpaRepository<Company, Long> {


    // TODO: Reporte - Empresas registradas en el sistema
    // TODO: Reporte que muestre la información de las empresas que están registradas por carrera
    @Query("select c from Company c")
    @Override
    List<Company> findAll();

    // TODO: Reporte - Mostrar que empresas han solicitado practicantes y cuáles no.
    @Query("select c from Company c where c.compIcesiStud = ?1")
    List<Company> findCompaniesByCompIcesiStud( boolean compIcesiStud);





}
