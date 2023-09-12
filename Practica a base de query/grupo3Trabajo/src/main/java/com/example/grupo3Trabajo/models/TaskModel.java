package com.example.grupo3Trabajo.models;

public class TaskModel {
    private long idTask; // atributo llave
    private long idEmergency;
    private long idTaskStatus;
    private long idRegion;
    private String name;
    private String description;

    public long getIdTask() {
        return idTask;
    }

    public void setIdTask(long idTask) {
        this.idTask = idTask;
    }

    public long getIdEmergency() {
        return idEmergency;
    }

    public void setIdEmergency(long idEmergency) {
        this.idEmergency = idEmergency;
    }

    public long getIdTaskStatus() {
        return idTaskStatus;
    }

    public void setIdTaskStatus(long idTaskStatus) {
        this.idTaskStatus = idTaskStatus;
    }

    public long getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(long idRegion) {
        this.idRegion = idRegion;
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
}
