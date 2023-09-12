package com.example.grupo3Trabajo.controllers;

import com.example.grupo3Trabajo.models.RegionModel;
import com.example.grupo3Trabajo.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RegionController {
    @Autowired
    RegionService regionService;

    @GetMapping("/region")
    public List<RegionModel> getAll(){
        return regionService.getAllRegions();
    }

    @GetMapping("/region/{id}")
    public List<RegionModel> getById(@PathVariable long id) {
        return regionService.getRegionById(id);
    }

    @PostMapping("/region/post")
    @ResponseBody
    public ResponseEntity<RegionModel> save(@RequestBody RegionModel regionModel){
        long idGenerado = regionService.createRegion(regionModel);
        regionModel.setIdRegion(idGenerado);
        return ResponseEntity.status(HttpStatus.CREATED).body(regionModel);
    }

    @PutMapping("/region/put/{id}")
    @ResponseBody
    public String update(@PathVariable long id, @RequestBody RegionModel regionModel) {
        return regionService.updateRegion(regionModel, id);
    }

    @DeleteMapping("/region/delete/{id}")
    public String delete(@PathVariable long id) {
        return regionService.deleteRegion(id);
    }
}
