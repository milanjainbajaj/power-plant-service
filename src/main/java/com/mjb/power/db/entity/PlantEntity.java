package com.mjb.power.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "PLANT_TABLE")
public class PlantEntity {
    @Id
    private int id;
    private int powerGenerationYear;
    @NotNull
    private String locationCode;
    @NotNull
    private String plantName;
    private String generatorId;
    private String generatorStatus;
    private int powerGeneration;
}
