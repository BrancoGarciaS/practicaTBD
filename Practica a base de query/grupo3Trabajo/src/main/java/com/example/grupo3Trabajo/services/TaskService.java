package com.example.grupo3Trabajo.services;

import com.example.grupo3Trabajo.models.EmergencyModel;
import com.example.grupo3Trabajo.models.RegionModel;
import com.example.grupo3Trabajo.models.TaskModel;
import com.example.grupo3Trabajo.models.TaskStatusModel;
import com.example.grupo3Trabajo.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

@Service
public class TaskService implements TaskRepository {
    @Autowired
    private Sql2o sql2o;

    // Para obtener todas las tareas
    @Override
    public List<TaskModel> getAllTasks() {
        try(Connection conn = sql2o.open()) {
            // selecciono toda la informacion de la tabla Task
            return conn.createQuery("SELECT * FROM task")
                    .executeAndFetch(TaskModel.class);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    // Para buscar una tarea por id
    @Override
    public List<TaskModel> getTaskById(long idTask){
        try(Connection conn = sql2o.open()) {
            return conn.createQuery("select * from task where idTask= :idTask")
                    .addParameter("idTask", idTask)
                    .executeAndFetch(TaskModel.class);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    // Para crear una nueva tarea
    @Override
    public long createTask(TaskModel taskModel) {
        try(Connection conn = sql2o.open()) {
            String sql = "INSERT INTO task (idEmergency, idTaskStatus, idRegion, name, description)" +
                    "VALUES (:idEmergency, :idTaskStatus, :idRegion, :name, :description)";
            Query query = conn.createQuery(sql, true)
                    .addParameter("idEmergency", taskModel.getIdEmergency())
                    .addParameter("idTaskStatus", taskModel.getIdTaskStatus())
                    .addParameter("idRegion", taskModel.getIdRegion())
                    .addParameter("name", taskModel.getName())
                    .addParameter("description", taskModel.getDescription());
            int idGenerado = (int) query.executeUpdate().getKey();
            return idGenerado; // retorno el id de la tarea generada
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return -1L;
        }
    }
    // Para actualizar la información de una tarea
    @Override
    public String updateTask(TaskModel taskModel, long idTask) {
        try (Connection conn = sql2o.open()) {
            String updateSql = "UPDATE task SET idEmergency = :idEmergency, idTaskStatus = :idTaskStatus, " +
                    "idRegion = :idRegion, name = :name, description = :description " +
                    "WHERE idTask = :idTask";
            conn.createQuery(updateSql)
                    .addParameter("idEmergency", taskModel.getIdEmergency())
                    .addParameter("idTaskStatus", taskModel.getIdTaskStatus())
                    .addParameter("idRegion", taskModel.getIdRegion())
                    .addParameter("name", taskModel.getName())
                    .addParameter("description", taskModel.getDescription())
                    .addParameter("idTask", idTask)
                    .executeUpdate();
            return "actualización realizada con éxito";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "fallo al realizar actualización";
        }
    }
    // Para borrar una emergencia (por id)
    @Override
    public String deleteTask(long idTask){
        try(Connection conn = sql2o.open()){
            conn.createQuery("DELETE from task where idTask = :idTask ")
                    .addParameter("idTask", idTask)
                    .executeUpdate();
            return "eliminacion por id realizada con exito";
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return "fallo al eliminar por id (el id proporcionado no existe)";
        }
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
    // Para obtener tarea por nombre
    @Override
    public List<TaskModel> getTaskByName(String name){
        try(Connection conn = sql2o.open()) {
            String getTaskByNamesql = "SELECT * " +
                    "FROM task " +
                    "WHERE \"name\" = :name ";
            return conn.createQuery(getTaskByNamesql)
                    .addParameter("name", name)
                    .executeAndFetch(TaskModel.class);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // Para obtener la emergencia
    @Override
    public List<EmergencyModel> getEmergency(long idTask){
        try(Connection conn = sql2o.open()) {
            String getEmergencysql = "select emergency.* " +
                    "from task, emergency " +
                    "where task.idTask = :idTask and task.idEmergency = emergency.idEmergency";
            return conn.createQuery(getEmergencysql)
                    .addParameter("idTask", idTask)
                    .executeAndFetch(EmergencyModel.class);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    // Para obtener el estado de la tarea
    @Override
    public List<TaskStatusModel> getTaskStatus(long idTask){
        try(Connection conn = sql2o.open()) {
            String getTaskStatussql = "select taskStatus.* " +
                    "from task, taskStatus " +
                    "where task.idTask = :idTask and task.idTaskStatus = taskStatus.idTaskStatus";
            return conn.createQuery(getTaskStatussql)
                    .addParameter("idTask", idTask)
                    .executeAndFetch(TaskStatusModel.class);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    // Para obtener la region de la tarea
    @Override
    public List<RegionModel> getRegion(long idTask){
        try(Connection conn = sql2o.open()) {
            String getRegionsql = "select region.* " +
                    "from task, region " +
                    "where task.idTask = :idTask and task.idRegion = region.idRegion";
            return conn.createQuery(getRegionsql)
                    .addParameter("idTask", idTask)
                    .executeAndFetch(RegionModel.class);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}