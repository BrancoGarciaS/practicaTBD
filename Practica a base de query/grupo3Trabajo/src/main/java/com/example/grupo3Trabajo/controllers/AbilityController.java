package com.example.grupo3Trabajo.controllers;

import com.example.grupo3Trabajo.models.AbilityModel;
import com.example.grupo3Trabajo.services.AbilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AbilityController {
    @Autowired
    AbilityService abilityService;

    @GetMapping("/ability")
    public List<AbilityModel> getAll(){
        return abilityService.getAllAbilities();
    }

    @GetMapping("/ability/{id}")
    public List<AbilityModel> getById(@PathVariable long id) {
        return abilityService.getAbilityById(id);
    }

    @PostMapping("/ability/post")
    @ResponseBody
    public ResponseEntity<AbilityModel> save(@RequestBody AbilityModel abilityModel){
        long idGenerado = abilityService.createAbility(abilityModel);
        abilityModel.setIdAbility(idGenerado);
        return ResponseEntity.status(HttpStatus.CREATED).body(abilityModel);
    }

    @PutMapping("/ability/put/{id}")
    @ResponseBody
    public String update(@PathVariable long id, @RequestBody AbilityModel abilityModel) {
        return abilityService.updateAbility(abilityModel, id);
    }

    @DeleteMapping("/ability/delete/{id}")
    public String delete(@PathVariable long id) {
        return abilityService.deleteAbility(id);
    }
}
