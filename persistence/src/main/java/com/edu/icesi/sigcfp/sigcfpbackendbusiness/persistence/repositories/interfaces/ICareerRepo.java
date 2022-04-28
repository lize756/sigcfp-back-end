package com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Career;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ICareerRepo extends JpaRepository<Career, Long> {


    // TODO: Obtener las carreras que pertenecen a una facultad por su id
    @Query(value = "SELECT * FROM CAREER C INNER JOIN FACULTY F ON C.FACULTY_FACU_ID = F.FACU_ID WHERE F.FACU_ID = ?1", nativeQuery = true)
    List<Career> findCareersByFacultyFacuId(Long facuId);


}
