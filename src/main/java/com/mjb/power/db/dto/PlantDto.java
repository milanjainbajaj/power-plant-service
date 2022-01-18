package com.mjb.power.db.dto;

public interface PlantDto {
    Integer getPowerGenerationYear();
    String getLocationCode();
    String getPlantName();
    Integer getPowerGenerationByPlant();
    Integer getPowerGenerationByLocation();
    Float getPowerGenerationPercent();
}
