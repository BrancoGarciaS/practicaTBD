package com.example.grupo3Trabajo.services;

import com.example.grupo3Trabajo.models.RegionModel;
import com.example.grupo3Trabajo.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

@Service
public class RegionService implements RegionRepository {
    @Autowired
    private Sql2o sql2o;

    // Para obtener todas las regiones
    @Override
    public List<RegionModel> getAllRegions() {
        try(Connection conn = sql2o.open()) {
            // selecciono toda la informacion de la tabla taskStatus
            return conn.createQuery("SELECT * FROM region")
                    .executeAndFetch(RegionModel.class);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    // Para buscar una region por id
    @Override
    public List<RegionModel> getRegionById(long idRegion){
        try(Connection conn = sql2o.open()) {
            return conn.createQuery("select * from region where idRegion= :idRegion")
                    .addParameter("idRegion", idRegion)
                    .executeAndFetch(RegionModel.class);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    // Para crear una nueva region
    @Override
    public long createRegion(RegionModel regionModel) {
        try(Connection conn = sql2o.open()) {
            String sql = "INSERT INTO region (name)" +
                    "VALUES (:name)";
            Query query = conn.createQuery(sql, true)
                    .addParameter("name", regionModel.getName());
            int idGenerado = (int) query.executeUpdate().getKey();
            return idGenerado; // retorno el id del estado tarea generado
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return -1L;
        }
    }
    // Para actualizar la información de una region
    @Override
    public String updateRegion(RegionModel regionModel, long idRegion) {
        try (Connection conn = sql2o.open()) {
            String updateSql = "UPDATE region SET name = :name " +
                    "WHERE idRegion = :idRegion";
            conn.createQuery(updateSql)
                    .addParameter("name", regionModel.getName())
                    .addParameter("idRegion", idRegion)
                    .executeUpdate();
            return "actualización realizada con éxito";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "fallo al realizar actualización";
        }
    }
    // Para borrar una region (por id)
    @Override
    public String deleteRegion(long idRegion){
        try(Connection conn = sql2o.open()){
            conn.createQuery("DELETE from region where idRegion = :idRegion ")
                    .addParameter("idRegion", idRegion)
                    .executeUpdate();
            return "eliminacion por id realizada con exito";
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return "fallo al eliminar por id (el id proporcionado no existe)";
        }
    }
}
