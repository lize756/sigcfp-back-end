package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Career;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.ICareerService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces.ICareerController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/careers")
@CrossOrigin(origins = "*")
//@PreAuthorize("")
public class CareerController implements ICareerController {

    ICareerService CareerService;

    @Autowired
    public CareerController(ICareerService CareerService) {
        this.CareerService = CareerService;
    }

    @Override
    @PostMapping("/add")
    public ResponseEntity<Career> addCareer(@RequestBody Career career) {
        return null;
    }

    @Override
    @PutMapping("/update")
    public ResponseEntity<Career> updateCareer(@PathVariable long careId, @RequestBody Career career) {
        return null;
    }

    @Override
    @GetMapping("/{careId}")
    public ResponseEntity<Career> getCareer(@PathVariable long careId) {
        return null;
    }

    @Override
    @DeleteMapping("/{careId}")
    public ResponseEntity<Career> deleteCareer(@PathVariable long careId) {
        return null;
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<List<Career>> getCareers() {
        return null;
    }
}
