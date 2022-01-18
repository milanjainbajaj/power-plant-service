package com.mjb.power.controller;

import com.mjb.power.db.dto.PlantDto;
import com.mjb.power.db.repository.PlantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/plant")
@Slf4j
@Validated
public class PlantController {
    @Autowired
    private PlantRepository plantRepository;

    @GetMapping
    ResponseEntity findAll(Pageable pageable) {
        return findPlant(null, null, pageable);
    }

    @GetMapping("/search")
    ResponseEntity findByLocationAndPlantName(@RequestParam(required = false) String location, @RequestParam(required = false) String name, Pageable pageable) {
        return findPlant(location, name, pageable);
    }

    private ResponseEntity findPlant(String locationCode, String plantName, Pageable pageable) {
        Page<PlantDto> plantDtoList = plantRepository.findPlant(locationCode, plantName, pageable);
        if (!plantDtoList.isEmpty()) {
            return ResponseEntity.ok(plantDtoList);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No data available for search parameter");
        }
    }
}
