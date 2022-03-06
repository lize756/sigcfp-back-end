package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Skill;

import java.util.List;

public interface ISkillService {

    Skill addSkill(Skill skill);

    Skill updateSkill(long skillId, Skill skill);

    Skill searchSkill(long skillId);

    Skill deleteSkill(long skillId);

    List<Skill> skills();

}
