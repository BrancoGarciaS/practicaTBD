package com.example.grupo3Trabajo.services;

import com.example.grupo3Trabajo.models.TaskStatusModel;
import com.example.grupo3Trabajo.repositories.TaskStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

@Service
public class TaskStatusService implements TaskStatusRepository {
    @Autowired
    private Sql2o sql2o;

    // Para obtener todos los estados de las tareas
    @Override
    //@Cacheable(value = "taskStatus")
    public List<TaskStatusModel> getAllStateTask() {
        try(Connection conn = sql2o.open()) {
            // selecciono toda la informacion de la tabla taskStatus
            return conn.createQuery("SELECT * FROM taskStatus")
                    .executeAndFetch(TaskStatusModel.class);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<TaskStatusModel> getStateTaskById(int idTaskStatus){
        try(Connection conn = sql2o.open()) {
            // String sql = "SELECT * FROM taskStatus where idStatus=:id";
            return conn.createQuery("select * from taskStatus where idTaskStatus= :idTaskStatus")
                    .addParameter("idTaskStatus", idTaskStatus)
                    .executeAndFetch(TaskStatusModel.class);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public int createTaskStatus(TaskStatusModel taskStatus) {
        try(Connection conn = sql2o.open()) {
            String sql = "INSERT INTO taskStatus (nameTaskStatus)" + "VALUES (:nameTaskStatus)";
            Query query = conn.createQuery(sql, true)
                    .addParameter("nameTaskStatus", taskStatus.getNameTaskStatus());
            int idGenerado = (int) query.executeUpdate().getKey();
            return idGenerado; // retorno el id del estado tarea generado
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    @Override
    public String updateTaskStatus(TaskStatusModel taskStatus, Integer idTaskStatus){
        try(Connection conn = sql2o.open()){
            String updateSql = "UPDATE taskStatus SET nameTaskStatus= :nameTaskStatus " +
                    "WHERE idTaskStatus = :idTaskStatus";
            conn.createQuery(updateSql)
                    .addParameter("nameTaskStatus", taskStatus.getNameTaskStatus())
                    .addParameter("idTaskStatus", idTaskStatus)
                    .executeUpdate();
            return "actualizacion realizada con exito";
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return "fallo al realizar actualizacion";
        }
    }
    @Override
    public String deleteTaskStatus(int idTaskStatus){
        try(Connection conn = sql2o.open()){
            conn.createQuery("DELETE from taskStatus where idTaskStatus = :idTaskStatus ")
                    .addParameter("idTaskStatus",idTaskStatus)
                    .executeUpdate();
            return "eliminacion por id realizada con exito";
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return "fallo al eliminar por id (el id proporcionado no existe)";
        }
    }







}
