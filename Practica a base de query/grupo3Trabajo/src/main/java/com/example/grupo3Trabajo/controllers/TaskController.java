package com.example.grupo3Trabajo.controllers;

import com.beust.jcommander.Parameter;
import com.example.grupo3Trabajo.models.EmergencyModel;
import com.example.grupo3Trabajo.models.RegionModel;
import com.example.grupo3Trabajo.models.TaskModel;
import com.example.grupo3Trabajo.models.TaskStatusModel;
import com.example.grupo3Trabajo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping("/task")
    public List<TaskModel> getAll(){
        return taskService.getAllTasks();
    }

    @GetMapping("/task/{id}")
    public List<TaskModel> getById(@PathVariable long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping("/task/post")
    @ResponseBody
    public ResponseEntity<TaskModel> save(@RequestBody TaskModel taskModel){
        long idGenerado = taskService.createTask(taskModel);
        taskModel.setIdTask(idGenerado);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskModel);
    }

    @PutMapping("/task/put/{id}")
    @ResponseBody
    public String update(@PathVariable long id, @RequestBody TaskModel taskModel) {
        return taskService.updateTask(taskModel, id);
    }

    @DeleteMapping("/task/delete/{id}")
    public String delete(@PathVariable long id) {
        return taskService.deleteTask(id);
    }

    @GetMapping("/task/name/{name}")
    public List<TaskModel> getTaskByName(@PathVariable String name){
        return taskService.getTaskByName(name);
    }

    @GetMapping("/task/emergency/{id}")
    public List<EmergencyModel> getEmergency(@PathVariable long id) {
        return taskService.getEmergency(id);
    }

    @GetMapping("/task/taskStatus/{id}")
    public List<TaskStatusModel> getTaskStatus(@PathVariable long id){
        return taskService.getTaskStatus(id);
    }

    @GetMapping("/task/region/{id}")
    public List<RegionModel> getRegion(@PathVariable long id){
        return taskService.getRegion(id);
    }
}
