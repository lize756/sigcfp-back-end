package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Ethnicgroup;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IEthnicgroupController {
    /**
     * Allow add new ethnic group in the system.
     *
     * @param ethnicgroup ethnic group to add.
     * @return a responseEntity that represent the whole HTTP response: status code,
     * headers, and body.
     */
    ResponseEntity<Ethnicgroup> addEthnicgroup(Ethnicgroup ethnicgroup);

    /**
     * Allow update a ethnic group.
     *
     * @param etgrId id of ethnic group to update.
     * @return a responseEntity that represent the whole HTTP response: status code,
     * headers, and body.
     */
    ResponseEntity<Ethnicgroup> updateEthnicgroup(long etgrId, Ethnicgroup ethnicgroup);

    ResponseEntity<Ethnicgroup> getEthnicgroup(long etgrId);

    /**
     * Allows delete a ethnic group through you id
     *
     * @param etgrId id of the ethnic group that you want to delete
     * @return a responseEntity that represent the whole HTTP response: status code,
     * headers, and body.
     */
    ResponseEntity<HttpStatus> deleteEthnicgroup(long etgrId);

    /**
     * Allows to obtain the list of ethnic groups saved in the database.
     *
     * @return a responseEntity that represent the whole HTTP response: status code,
     * headers, and body.
     */
    ResponseEntity<List<Ethnicgroup>> getEthnicgroups();

}
