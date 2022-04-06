package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Curriculum;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Permmission;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.IPermmissionService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces.IPermmissionController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/permmissions")
@CrossOrigin(origins = "*")
//@PreAuthorize("")
public class PermmissionController implements IPermmissionController {

    private IPermmissionService iPermmissionService;

    @Autowired
    public PermmissionController(IPermmissionService iPermmissionService) {
        this.iPermmissionService = iPermmissionService;
    }

    @Override
    @PostMapping("/add")
    public ResponseEntity<Permmission> addPermmission(@RequestBody Permmission permmission) {
        try {
            Permmission _permmission = iPermmissionService.addPermmission(permmission);
            return new ResponseEntity<Permmission>(_permmission, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PutMapping("/update/{permId}")
    public ResponseEntity<Permmission> updatePermmission(@PathVariable("permId") long permId, @RequestBody Permmission permmission) {
        Optional<Permmission> optionalPermmission = Optional.of( iPermmissionService.searchPermmission(permId));
        if (optionalPermmission.isPresent()) {
            Permmission _permmission = optionalPermmission.get();
            return new ResponseEntity<>(iPermmissionService.updatePermmission(_permmission), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @GetMapping("/{permId}")
    public ResponseEntity<Permmission> getPermmission(@PathVariable("permId") long permId) {
        Optional<Permmission> optionalPermmission = Optional.of(iPermmissionService.searchPermmission(permId));
        if (optionalPermmission.isPresent()) {
            return new ResponseEntity<>(optionalPermmission.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @DeleteMapping("/{permId}")
    public ResponseEntity<HttpStatus> deletePermmissión(@PathVariable("permId")  long permId) {
        try {
            iPermmissionService.deletePermmission(permId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Permmission>> getPermmissions() {
        try {
            List<Permmission> permmissionList = iPermmissionService.permmissions();
            if (permmissionList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(permmissionList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
