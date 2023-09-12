package com.example.grupo3Trabajo.repositories;

import com.example.grupo3Trabajo.models.EmergencyModel;
import com.example.grupo3Trabajo.models.RegionModel;
import com.example.grupo3Trabajo.models.TaskModel;
import com.example.grupo3Trabajo.models.TaskStatusModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository {
    public List<TaskModel> getAllTasks();
    public List<TaskModel> getTaskById(long idTask);
    public long createTask(TaskModel taskModel);
    public String updateTask(TaskModel taskModel, long idTask);
    public String deleteTask(long idTask);
    public List<TaskModel> getTaskByName(String name); // para obtener las tareas por nombre
    public List<EmergencyModel> getEmergency(long idTask); // para obtener la emergencia directamente
    public List<TaskStatusModel> getTaskStatus(long idTask); // para obtener el estado de la tarea directamente
    public List<RegionModel> getRegion(long idTask); // para obtener la region de la tarea directamente
}
