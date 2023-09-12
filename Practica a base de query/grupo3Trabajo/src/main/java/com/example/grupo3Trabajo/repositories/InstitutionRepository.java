package com.example.grupo3Trabajo.repositories;

import com.example.grupo3Trabajo.models.InstitutionModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstitutionRepository {
    public List<InstitutionModel> getAllInstitutions();
    public List<InstitutionModel> getInstitutionById(long idInstitution);
    public long createInstitution(InstitutionModel institutionModel);
    public String updateInstitution(InstitutionModel institutionModel, long idInstitution);
    public String deleteInstitution(long idInstitution);
}
