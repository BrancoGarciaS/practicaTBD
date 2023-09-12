package com.example.grupo3Trabajo.controllers;

import com.example.grupo3Trabajo.models.EmergencyModel;
import com.example.grupo3Trabajo.services.EmergencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmergencyController {
    @Autowired
    EmergencyService emergencyService;

    @GetMapping("/emergency")
    public List<EmergencyModel> getAll(){
        return emergencyService.getAllEmergencies();
    }

    @GetMapping("/emergency/{id}")
    public List<EmergencyModel> getById(@PathVariable long id) {
        return emergencyService.getEmergencyById(id);
    }

    @PostMapping("/emergency/post")
    @ResponseBody
    public ResponseEntity<EmergencyModel> save(@RequestBody EmergencyModel emergencyModel){
        long idGenerado = emergencyService.createEmergency(emergencyModel);
        emergencyModel.setIdEmergency(idGenerado);
        return ResponseEntity.status(HttpStatus.CREATED).body(emergencyModel);
    }

    @PutMapping("/emergency/put/{id}")
    @ResponseBody
    public String update(@PathVariable long id, @RequestBody EmergencyModel emergencyModel) {
        return emergencyService.updateEmergency(emergencyModel, id);
    }

    @DeleteMapping("/emergency/delete/{id}")
    public String delete(@PathVariable long id) {
        return emergencyService.deleteEmergency(id);
    }
}
