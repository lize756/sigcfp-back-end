package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Skill;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.ISkillService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence.repositories.interfaces.ISkillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SkillService implements ISkillService {

    ISkillRepo iSkillRepo;

    @Autowired
    public SkillService(ISkillRepo iSkillRepo) {
        this.iSkillRepo = iSkillRepo;
    }

    @Override
    @Transactional
    public Skill addSkill(Skill skill) {
        if (!iSkillRepo.existsById(skill.getSkilId())) {
            return iSkillRepo.save(skill);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Skill updateSkill(long skillId, Skill skill) {
        if (iSkillRepo.existsById(skillId)) {
            return iSkillRepo.save(skill);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Skill searchSkill(long skillId) {
        if (iSkillRepo.existsById(skillId)) {
            return iSkillRepo.getById(skillId);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Skill deleteSkill(long skillId) {
        Skill skillToDelete = null;
        if (iSkillRepo.existsById(skillId)) {
            skillToDelete = iSkillRepo.findById(skillId).get();
            iSkillRepo.delete(iSkillRepo.getById(skillId));
        } else {
            return null;
        }
        return skillToDelete;
    }

    @Override
    @Transactional
    public List<Skill> skills() {
        return iSkillRepo.findAll();
    }
}
