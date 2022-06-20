package com.ioco.apocalypse.survivor.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public interface SurvivorInterface {
    public String getInfectionStatus();
    public Integer getInfectionPercentage();
}
