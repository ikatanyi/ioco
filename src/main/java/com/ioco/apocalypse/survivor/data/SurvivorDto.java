package com.ioco.apocalypse.survivor.data;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ioco.apocalypse.resource.data.ResourceDto;
import com.ioco.apocalypse.resource.model.Resource;
import com.ioco.apocalypse.survivor.model.Survivor;
import com.ioco.apocalypse.survivor.model.enumeration.Gender;
import com.ioco.apocalypse.survivor.model.enumeration.InfectionStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SurvivorDto {
    @Schema(hidden = true)
    private Long id;
    @NotNull
    private String name;
    private Integer age=0;
    @Enumerated(EnumType.STRING)
    private Gender gender = Gender.OTHER;
    private String latitude;
    private String longitude;

    @Schema(hidden = true)
    @Enumerated(EnumType.STRING)
    private InfectionStatus infectionStatus = InfectionStatus.NOT_INFECTED;

    @Schema(hidden = true)
    private Integer infectionReportCount=0;

    private List<ResourceDto>resources = new ArrayList<>();

    public Survivor toSurvivor(){
        Survivor survivor = new Survivor();
        LocalDate now = LocalDate.now();
        survivor.setGender(this.gender);
        survivor.setName(this.name);
        survivor.setDob(now.minusYears(this.getAge()));
        survivor.setLatitude(this.latitude);
        survivor.setLongitude(this.getLongitude());
        survivor.setInfectionStatus(this.infectionStatus);
        survivor.addResources(this.resources.stream().map(ResourceDto::toResource).collect(Collectors.toList()));
        return survivor;
    }
}
