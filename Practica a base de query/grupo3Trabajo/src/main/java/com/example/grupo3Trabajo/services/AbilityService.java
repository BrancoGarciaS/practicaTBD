package com.example.grupo3Trabajo.services;

import com.example.grupo3Trabajo.models.AbilityModel;
import com.example.grupo3Trabajo.repositories.AbilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

@Service
public class AbilityService implements AbilityRepository {
    @Autowired
    private Sql2o sql2o;

    // Para obtener todas las habilidades
    @Override
    public List<AbilityModel> getAllAbilities() {
        try(Connection conn = sql2o.open()) {
            // selecciono toda la informacion de la tabla taskStatus
            return conn.createQuery("SELECT * FROM ability")
                    .executeAndFetch(AbilityModel.class);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    // Para buscar una habilidad por id
    @Override
    public List<AbilityModel> getAbilityById(long idAbility){
        try(Connection conn = sql2o.open()) {
            // String sql = "SELECT * FROM taskStatus where idStatus=:id";
            return conn.createQuery("select * from ability where idAbility= :idAbility")
                    .addParameter("idAbility", idAbility)
                    .executeAndFetch(AbilityModel.class);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    // Para crear una nueva habilidad
    @Override
    public long createAbility(AbilityModel abilityModel) {
        try(Connection conn = sql2o.open()) {
            String sql = "INSERT INTO ability (name, description)" +
                    "VALUES (:name, :description)";
            Query query = conn.createQuery(sql, true)
                    .addParameter("name", abilityModel.getName())
                    .addParameter("description", abilityModel.getDescription());
            int idGenerado = (int) query.executeUpdate().getKey();
            return idGenerado; // retorno el id del estado tarea generado
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return -1L;
        }
    }
    // Para actualizar la información de una habilidad
    @Override
    public String updateAbility(AbilityModel abilityModel, long idAbility) {
        try (Connection conn = sql2o.open()) {
            String updateSql = "UPDATE ability SET name = :name, description = :description " +
                    "WHERE idAbility = :idAbility";
            conn.createQuery(updateSql)
                    .addParameter("name", abilityModel.getName())
                    .addParameter("description", abilityModel.getDescription())
                    .addParameter("idAbility", idAbility)
                    .executeUpdate();
            return "actualización realizada con éxito";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "fallo al realizar actualización";
        }
    }

    // Para borrar una habilidad (por id)
    @Override
    public String deleteAbility(long idAbility){
        try(Connection conn = sql2o.open()){
            conn.createQuery("DELETE from ability where idAbility = :idAbility ")
                    .addParameter("idAbility",idAbility)
                    .executeUpdate();
            return "eliminacion por id realizada con exito";
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return "fallo al eliminar por id (el id proporcionado no existe)";
        }
    }
}