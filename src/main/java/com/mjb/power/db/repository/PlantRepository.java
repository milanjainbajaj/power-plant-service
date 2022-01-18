package com.mjb.power.db.repository;

import com.mjb.power.db.dto.PlantDto;
import com.mjb.power.db.entity.PlantEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PlantRepository extends PagingAndSortingRepository<PlantEntity, Integer> {
    @Query(
            value = " SELECT powerGenerationYear, locationCode, plantName, powerGenerationByPlant, " +
                    " ROUND(((powerGenerationByPlant*1.00)/(powerGenerationByLocation*1.00))*100, 2) AS powerGenerationPercent " +
                    " FROM " +
                    " ( SELECT p.POWER_GENERATION_YEAR as powerGenerationYear, p.LOCATION_CODE as locationCode, " +
                    " p.PLANT_NAME as plantName, SUM(p.POWER_GENERATION) as powerGenerationByPlant, l.powerGenerationByLocation " +
                    " FROM PLANT_TABLE p " +
                    "   JOIN " +
                    "       (" +
                    "           SELECT lt.POWER_GENERATION_YEAR, lt.LOCATION_CODE, SUM(lt.POWER_GENERATION) powerGenerationByLocation " +
                    "           FROM PLANT_TABLE lt " +
                    "           GROUP BY lt.POWER_GENERATION_YEAR, lt.LOCATION_CODE " +
                    "       ) as l ON l.LOCATION_CODE = p.LOCATION_CODE" +
                    " WHERE (coalesce(:locationCode, null) is null or p.LOCATION_CODE=:locationCode) " +
                    " AND (coalesce(:plantName, null) is null or p.PLANT_NAME=:plantName) " +
                    " GROUP BY p.POWER_GENERATION_YEAR, p.LOCATION_CODE, p.PLANT_NAME, l.powerGenerationByLocation) d",
            countQuery = "SELECT COUNT(DISTINCT p.PLANT_NAME) " +
                    " FROM PLANT_TABLE p " +
                    " WHERE (coalesce(:locationCode, null) is null or p.LOCATION_CODE=:locationCode) " +
                    " AND (coalesce(:plantName, null) is null or p.PLANT_NAME=:plantName) ",
            nativeQuery = true
    )
    Page<PlantDto> findPlant(String locationCode, String plantName, Pageable pageable);
}
