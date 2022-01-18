package com.mjb.power.helper;

import com.mjb.power.db.dto.PlantDto;

public class PlantDtoImpl implements PlantDto {
    @Override
    public Integer getPowerGenerationYear() {
        return 2019;
    }

    @Override
    public String getLocationCode() {
        return "AK";
    }

    @Override
    public String getPlantName() {
        return "Testing";
    }

    @Override
    public Integer getPowerGenerationByPlant() {
        return 10;
    }

    @Override
    public Integer getPowerGenerationByLocation() {
        return 100;
    }

    @Override
    public Float getPowerGenerationPercent() {
        return 10F;
    }
}
