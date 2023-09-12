package com.example.grupo3Trabajo.repositories;

import com.example.grupo3Trabajo.models.RegionModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository {
    public List<RegionModel> getAllRegions();
    public List<RegionModel> getRegionById(long idRegion);
    public long createRegion(RegionModel regionModel);
    public String updateRegion(RegionModel regionModel, long idRegion);
    public String deleteRegion(long idRegion);
}
