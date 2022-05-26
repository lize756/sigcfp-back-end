package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Curriculum;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.ICurriculumService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces.ICurriculumController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/api/curriculums")
@CrossOrigin(origins = "*")
//@PreAuthorize("")
public class CurriculumController implements ICurriculumController {

    private ICurriculumService iCurriculumService;

    @Autowired
    public CurriculumController(ICurriculumService iCurriculumService) {
        this.iCurriculumService = iCurriculumService;
    }

    @Override
    @PostMapping("/add")
    public ResponseEntity<Curriculum> addCurriculum(@RequestBody Curriculum curriculum) {
        try {
            Curriculum _curriculum = iCurriculumService.addCurriculum(curriculum);
            return new ResponseEntity<Curriculum>(_curriculum, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PutMapping("/update/{curriId}")
    public ResponseEntity<Curriculum> updateCurriculum(@PathVariable("curriId") long curriId, @RequestBody Curriculum curriculum) {
        Optional<Curriculum> curriculumOptional = Optional.of(iCurriculumService.searchCurriculum(curriId));
        if (curriculumOptional.isPresent()) {
            Curriculum _curriculum = curriculumOptional.get();
            return new ResponseEntity<>(iCurriculumService.updateCurriculum(_curriculum), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @GetMapping("/{curriId}")
    public ResponseEntity<Curriculum> getCurriculum(@PathVariable("curriId") long curriId) {
        Optional<Curriculum> curriculumOptional = Optional.of(iCurriculumService.searchCurriculum(curriId));
        if (curriculumOptional.isPresent()) {
            return new ResponseEntity<>(curriculumOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @DeleteMapping("/{corriId}")
    public ResponseEntity<HttpStatus> deleteCurriculum(@PathVariable("curriId") long corriId) {
        try {
            iCurriculumService.deleteCurriculum(corriId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping()
    public ResponseEntity<List<Curriculum>> getCurriculums() {
        try {
            List<Curriculum> curriculumList = iCurriculumService.Curriculums();
            if (curriculumList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(curriculumList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
