package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Precondition;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPreconditionService {
    Precondition addPrecondition(Precondition precondition);

    Precondition updatePrecondition(Precondition precondition);

    Precondition searchPrecondition(long precondId);

    Precondition deletePrecondition(long precondId);

    List<Precondition> preconditions();
}
