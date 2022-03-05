package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Curriculum;

import java.util.List;

public interface ICurriculumService {

    Curriculum addCurriculum(Curriculum curriculum);

    Curriculum updateCurriculum(long currId, Curriculum curriculum);

    Curriculum searchCurriculum(long currId);

    Curriculum deleteCurriculum(long currId);

    List<Curriculum> Curriculums();
}
