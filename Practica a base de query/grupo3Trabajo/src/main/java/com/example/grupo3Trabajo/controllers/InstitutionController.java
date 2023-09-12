package com.example.grupo3Trabajo.controllers;
import com.example.grupo3Trabajo.models.InstitutionModel;
import com.example.grupo3Trabajo.services.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InstitutionController {
    @Autowired
    InstitutionService institutionService;

    @GetMapping("/institution")
    public List<InstitutionModel> getAll(){
        return institutionService.getAllInstitutions();
    }

    @GetMapping("/institution/{id}")
    public List<InstitutionModel> getById(@PathVariable long id) {
        return institutionService.getInstitutionById(id);
    }

    @PostMapping("/institution/post")
    @ResponseBody
    public ResponseEntity<InstitutionModel> save(@RequestBody InstitutionModel institutionModel){
        long idGenerado = institutionService.createInstitution(institutionModel);
        institutionModel.setIdInstitution(idGenerado);
        return ResponseEntity.status(HttpStatus.CREATED).body(institutionModel);
    }

    @PutMapping("/institution/put/{id}")
    @ResponseBody
    public String update(@PathVariable long id, @RequestBody InstitutionModel institutionModel) {
        return institutionService.updateInstitution(institutionModel, id);
    }

    @DeleteMapping("/institution/delete/{id}")
    public String delete(@PathVariable long id) {
        return institutionService.deleteInstitution(id);
    }
}
