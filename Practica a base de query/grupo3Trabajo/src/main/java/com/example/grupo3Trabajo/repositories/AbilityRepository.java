package com.example.grupo3Trabajo.repositories;

import com.example.grupo3Trabajo.models.AbilityModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbilityRepository {
    public List<AbilityModel> getAllAbilities();
    public List<AbilityModel> getAbilityById(long idAbility);
    public long createAbility(AbilityModel abilityModel);
    public String updateAbility(AbilityModel abilityModel, long idAbility);
    public String deleteAbility(long idAbility);
}
