package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Contact;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Ethnicgroup;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IEthnicgroupController {

    ResponseEntity<Ethnicgroup> addEthnicgroup(Ethnicgroup ethnicgroup);

    ResponseEntity<Ethnicgroup> updateEthnicgroup( long etgrId, Ethnicgroup contact);

    ResponseEntity<Ethnicgroup> getEthnicgroup(long etgrId);

    ResponseEntity<Ethnicgroup> deleteEthnicgroup(long etgrId);

    ResponseEntity<List<Ethnicgroup>> getEthnicgroups();

}
