package com.example.grupo3Trabajo.repositories;

import com.example.grupo3Trabajo.models.EmergencyModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmergencyRepository {
    public List<EmergencyModel> getAllEmergencies();
    public List<EmergencyModel> getEmergencyById(long idEmergency);
    public long createEmergency(EmergencyModel emergencyModel);
    public String updateEmergency(EmergencyModel emergencyModel, long idEmergency);
    public String deleteEmergency(long idEmergency);
}
