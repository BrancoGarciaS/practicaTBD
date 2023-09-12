package com.example.grupo3Trabajo.controllers;

import com.example.grupo3Trabajo.models.TaskStatusModel;
import com.example.grupo3Trabajo.services.TaskStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskStatusController {
    @Autowired
    TaskStatusService taskStatusService;

    @GetMapping("/taskStatus")
    public List<TaskStatusModel> getAll(){
        return taskStatusService.getAllStateTask();
    }

    @GetMapping("/taskStatus/{id}")
    public List<TaskStatusModel> getById(@PathVariable int id) {
        return taskStatusService.getStateTaskById(id);
    }

    @PostMapping("/postTaskStatus")
    @ResponseBody
    public ResponseEntity<TaskStatusModel> save(@RequestBody TaskStatusModel taskStatusModel){
        int idGenerado = taskStatusService.createTaskStatus(taskStatusModel);
        taskStatusModel.setIdTaskStatus(idGenerado);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskStatusModel);
    }

    @PutMapping("/putTaskStatus/{id}")
    @ResponseBody
    public String update(@PathVariable int id, @RequestBody TaskStatusModel taskStatusModel) {
        return taskStatusService.updateTaskStatus(taskStatusModel, id);
    }

    @DeleteMapping("/deleteTaskStatus/{id}")
    public String delete(@PathVariable int id) {
        return taskStatusService.deleteTaskStatus(id);
    }

}
