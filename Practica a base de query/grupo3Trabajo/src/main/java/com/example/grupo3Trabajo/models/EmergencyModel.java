package com.example.grupo3Trabajo.models;

public class EmergencyModel {
    private long idEmergency;
    private long idInstitution;
    private String name;
    private String description;
    private String location;

    public long getIdEmergency() {
        return idEmergency;
    }

    public void setIdEmergency(long idEmergency) {
        this.idEmergency = idEmergency;
    }

    public long getIdInstitution() {
        return idInstitution;
    }

    public void setIdInstitution(long idInstitution) {
        this.idInstitution = idInstitution;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
