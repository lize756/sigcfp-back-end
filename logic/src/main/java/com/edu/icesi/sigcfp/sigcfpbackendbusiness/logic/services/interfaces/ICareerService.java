package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Career;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.exceptions.ExistException;

import java.util.List;

public interface ICareerService {

    Career addCareer(Career career);

    Career updateCareer(long careId, Career career);

    Career searchCareer(long careId);

    Career deleteCareer(long careId);

    List<Career> careers();
}
