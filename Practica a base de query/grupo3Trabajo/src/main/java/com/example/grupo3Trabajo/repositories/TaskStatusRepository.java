package com.example.grupo3Trabajo.repositories;

import com.example.grupo3Trabajo.models.TaskStatusModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskStatusRepository {
    List<TaskStatusModel> getAllStateTask(); // para obtener todos los estados de las tareas
    List<TaskStatusModel> getStateTaskById(int idTaskStatus); // para obtener estado de tarea por id
    int createTaskStatus(TaskStatusModel taskStatus); // para crear un estado de tarea
    public String updateTaskStatus(TaskStatusModel taskStatus, Integer idTaskStatus);
    public String deleteTaskStatus(int id);


}
