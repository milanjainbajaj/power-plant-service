package com.mjb.power.db;

import com.mjb.power.db.dto.PlantDto;
import com.mjb.power.db.entity.PlantEntity;
import com.mjb.power.db.repository.PlantRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
public class PlantRepositoryTest {
    @Autowired
    private PlantRepository plantRepository;

    @Test
    @Rollback(false)
    public void testSavePlant(){
        Pageable pageable = PageRequest.of(0, 20);
        PlantEntity plantEntity = PlantEntity
                .builder()
                .id(1)
                .plantName("Test Plant")
                .powerGenerationYear(2019)
                .generatorId("1")
                .locationCode("AK")
                .generatorStatus("OP")
                .powerGeneration(100)
                .build();

        Assertions.assertEquals(plantRepository.save(plantEntity).getId(), 1);
        Page<PlantDto> plantDtoPage = plantRepository.findPlant(null, null, pageable);
        Assertions.assertNotNull(plantDtoPage);
        Assertions.assertEquals(plantDtoPage.getTotalElements(), 1);
    }
}
