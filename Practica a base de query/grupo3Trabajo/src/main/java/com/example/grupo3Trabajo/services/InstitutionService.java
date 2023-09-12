package com.example.grupo3Trabajo.services;

import com.example.grupo3Trabajo.models.InstitutionModel;
import com.example.grupo3Trabajo.repositories.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

@Service
public class InstitutionService implements InstitutionRepository {
    @Autowired
    private Sql2o sql2o;

    // Para obtener todas las instituciones
    @Override
    public List<InstitutionModel> getAllInstitutions() {
        try(Connection conn = sql2o.open()) {
            // selecciono toda la informacion de la tabla taskStatus
            return conn.createQuery("SELECT * FROM institution")
                    .executeAndFetch(InstitutionModel.class);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    // Para buscar una institucion por id
    @Override
    public List<InstitutionModel> getInstitutionById(long idInstitution){
        try(Connection conn = sql2o.open()) {
            return conn.createQuery("select * from institution where idInstitution= :idInstitution")
                    .addParameter("idInstitution", idInstitution)
                    .executeAndFetch(InstitutionModel.class);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    // Para crear una nueva institucion
    @Override
    public long createInstitution(InstitutionModel institutionModel) {
        try(Connection conn = sql2o.open()) {
            String sql = "INSERT INTO institution (name, description)" +
                    "VALUES (:name, :description)";
            Query query = conn.createQuery(sql, true)
                    .addParameter("name", institutionModel.getName())
                    .addParameter("description", institutionModel.getDescription());
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
    public String updateInstitution(InstitutionModel institutionModel, long idInstitution) {
        try (Connection conn = sql2o.open()) {
            String updateSql = "UPDATE institution SET name = :name, description = :description " +
                    "WHERE idInstitution = :idInstitution";
            conn.createQuery(updateSql)
                    .addParameter("name", institutionModel.getName())
                    .addParameter("description", institutionModel.getDescription())
                    .addParameter("idInstitution", idInstitution)
                    .executeUpdate();
            return "actualización realizada con éxito";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "fallo al realizar actualización";
        }
    }
    // Para borrar una habilidad (por id)
    @Override
    public String deleteInstitution(long idInstitution){
        try(Connection conn = sql2o.open()){
            conn.createQuery("DELETE from institution where idInstitution = :idInstitution ")
                    .addParameter("idInstitution", idInstitution)
                    .executeUpdate();
            return "eliminacion por id realizada con exito";
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return "fallo al eliminar por id (el id proporcionado no existe)";
        }
    }

}
