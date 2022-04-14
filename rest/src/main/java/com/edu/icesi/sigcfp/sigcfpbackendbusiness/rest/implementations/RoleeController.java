package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.implementations;

import com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities.Rolee;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic.services.interfaces.IRoleeService;
import com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest.interfaces.IRoleeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/api/rolees")
@CrossOrigin(origins = "*")
//@PreAuthorize("")
public class RoleeController implements IRoleeController {

    private IRoleeService iRoleeService;

    @Autowired
    public RoleeController(IRoleeService iRoleeService) {
        this.iRoleeService = iRoleeService;
    }

    @Override
    @PostMapping("/add")
    public ResponseEntity<Rolee> addRolee(@RequestBody Rolee rolee) {
        try {
            Rolee _rolee = iRoleeService.addRolee(rolee);
            return new ResponseEntity<Rolee>(_rolee, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PutMapping("/update/{roleId}")
    public ResponseEntity<Rolee> updateRolee(@PathVariable("roleId") long roleId, @RequestBody Rolee rolee) {
        Optional<Rolee> optionalRolee = Optional.of(iRoleeService.searchRolee(roleId));
        if (optionalRolee.isPresent()) {
            Rolee _rolee = optionalRolee.get();
            return new ResponseEntity<>(iRoleeService.updateRolee(_rolee), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @GetMapping("/{roleId}")
    public ResponseEntity<Rolee> getRolee(@PathVariable("roleId") long roleId) {
        Optional<Rolee> optionalRolee = Optional.of(iRoleeService.searchRolee(roleId));
        if (optionalRolee.isPresent()) {
            return new ResponseEntity<>(optionalRolee.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @DeleteMapping("/{roleId}")
    public ResponseEntity<HttpStatus> deleteRolee(@PathVariable("roleId") long roleId) {
        try {
            iRoleeService.deleteRolee(roleId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping()
    public ResponseEntity<List<Rolee>> getRolees() {
        try {
            List<Rolee> roleeList = iRoleeService.rolees();
            if (roleeList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(roleeList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
