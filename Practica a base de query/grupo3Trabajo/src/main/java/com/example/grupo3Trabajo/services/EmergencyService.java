package com.example.grupo3Trabajo.services;

import com.example.grupo3Trabajo.models.EmergencyModel;
import com.example.grupo3Trabajo.repositories.EmergencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

@Service
public class EmergencyService implements EmergencyRepository {
    @Autowired
    private Sql2o sql2o;

    // Para obtener todas las instituciones
    @Override
    public List<EmergencyModel> getAllEmergencies() {
        try(Connection conn = sql2o.open()) {
            // selecciono toda la informacion de la tabla taskStatus
            return conn.createQuery("SELECT * FROM emergency")
                    .executeAndFetch(EmergencyModel.class);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    // Para buscar una emergencia por id
    @Override
    public List<EmergencyModel> getEmergencyById(long idEmergency){
        try(Connection conn = sql2o.open()) {
            return conn.createQuery("select * from emergency where idEmergency= :idEmergency")
                    .addParameter("idEmergency", idEmergency)
                    .executeAndFetch(EmergencyModel.class);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    // Para crear una nueva emergencia
    @Override
    public long createEmergency(EmergencyModel emergencyModel) {
        try(Connection conn = sql2o.open()) {
            String sql = "INSERT INTO emergency (idInstitution, name, description, location)" +
                    "VALUES (:idInstitution, :name, :description, :location)";
            Query query = conn.createQuery(sql, true)
                    .addParameter("idInstitution", emergencyModel.getIdInstitution())
                    .addParameter("name", emergencyModel.getName())
                    .addParameter("description", emergencyModel.getDescription())
                    .addParameter("location", emergencyModel.getLocation());
            int idGenerado = (int) query.executeUpdate().getKey();
            return idGenerado; // retorno el id del estado tarea generado
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return -1L;
        }
    }
    // Para actualizar la información de una emergencia
    @Override
    public String updateEmergency(EmergencyModel emergencyModel, long idEmergency) {
        try (Connection conn = sql2o.open()) {
            String updateSql = "UPDATE emergency SET idInstitution = :idInstitution, " +
                    "name = :name, description = :description, location = :location " +
                    "WHERE idEmergency = :idEmergency";
            conn.createQuery(updateSql)
                    .addParameter("idInstitution", emergencyModel.getIdInstitution())
                    .addParameter("name", emergencyModel.getName())
                    .addParameter("description", emergencyModel.getDescription())
                    .addParameter("location", emergencyModel.getLocation())
                    .addParameter("idEmergency", idEmergency)
                    .executeUpdate();
            return "actualización realizada con éxito";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "fallo al realizar actualización";
        }
    }
    // Para borrar una emergencia (por id)
    @Override
    public String deleteEmergency(long idEmergency){
        try(Connection conn = sql2o.open()){
            conn.createQuery("DELETE from emergency where idEmergency = :idEmergency ")
                    .addParameter("idEmergency", idEmergency)
                    .executeUpdate();
            return "eliminacion por id realizada con exito";
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return "fallo al eliminar por id (el id proporcionado no existe)";
        }
    }
}
