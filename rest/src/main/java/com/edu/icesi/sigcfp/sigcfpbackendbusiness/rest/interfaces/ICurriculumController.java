package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Curriculum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICurriculumController {

    ResponseEntity<Curriculum> addCurriculum(Curriculum curriculum);

    ResponseEntity<Curriculum> updateCurriculum(long curriId, Curriculum curriculum);

    ResponseEntity<Curriculum> getCurriculum(long curriId);

    ResponseEntity<HttpStatus> deleteCurriculum(long corriId);

    ResponseEntity<List<Curriculum>> getCurriculums();

}
