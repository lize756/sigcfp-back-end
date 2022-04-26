package com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Career;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Company;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.InternRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IInternRequestRepo extends JpaRepository<InternRequest, Long> {

    @Override
    List<InternRequest> findAll();


    List<InternRequest> findInternRequestsByInteRequIsinprocess(String isInProcess);

    /**
     * Allow search the intern request associated with a companies
     *
     * @param compId
     * @return
     */
    // TODO: Reporte - Mostrar la cantidad de solitudes realizas por las empresas.
    @Query("select inReq from InternRequest inReq where inReq.company.compId = ?1")
    List<InternRequest> findInternRequestsByCompany(long compId);





    @Query("select i from InternRequest i where i.careers = ?1") // Tengo mis dudas
    List<InternRequest> findInternRequestsByCareers(long careId);



    // TODO: Contar el número de practicantes de una empresa por el id de la empresa
    @Query(value = "SELECT COUNT(IR.INTE_REQU_NUMBER) FROM INTERN_REQUEST IR INNER JOIN COMPANY C ON IR.COMPANY_COMP_ID = C.COMP_ID WHERE IR.COMPANY_COMP_ID = ?1;", nativeQuery = true)
    int countInternRequestByCompanyId(long compId);


    // TODO: Obtener la cantidad de solicitudes realizas por las empresas
    @Query(value = "SELECT COUNT(IR.INTE_REQU_NUMBER) FROM INTERN_REQUEST IR INNER JOIN COMPANY C2 on C2.COMP_ID = IR.COMPANY_COMP_ID WHERE C2.COMP_ID = ?1", nativeQuery = true)
    List<InternRequest> findInternRequestsByCompanyCompId(long compId);


}
