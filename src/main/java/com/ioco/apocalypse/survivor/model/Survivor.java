package com.ioco.apocalypse.survivor.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.ioco.apocalypse.resource.model.Resource;
import com.ioco.apocalypse.survivor.data.SurvivorDto;
import com.ioco.apocalypse.survivor.model.enumeration.Gender;
import com.ioco.apocalypse.survivor.model.enumeration.InfectionStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name = "survivor")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Survivor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private LocalDate dob;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String latitude;
    private String longitude;

    @Schema(hidden = true)
    @Enumerated(EnumType.STRING)
    private InfectionStatus infectionStatus=InfectionStatus.NOT_INFECTED;

    @OneToMany(mappedBy = "survivor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resource>resource = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "infectedSurvivor", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<InfectionReporter> reporters = new HashSet<>();

    public void addResources(List<Resource> resources) {
        this.resource = resources;
        this.resource.forEach(x -> x.setSurvivor(this));
    }

    public void addResource(Resource resource) {
        resource.setSurvivor(this);
        this.resource.add(resource);
    }

    public void addReporters(Set<InfectionReporter> reporters) {
        this.reporters = reporters;
        this.reporters.forEach(x -> x.setInfectionReporter(this));
    }

    public void addReport(InfectionReporter reporter) {
        reporter.setInfectedSurvivor(this);
        this.reporters.add(reporter);
    }

    public SurvivorDto toSurvivorDto(){
        SurvivorDto survivorDto = new SurvivorDto();
        survivorDto.setId(this.id);
        survivorDto.setAge(Period.between(this.getDob(),LocalDate.now()).getYears());
        survivorDto.setGender(this.gender);
        survivorDto.setName(this.name);
        survivorDto.setLatitude(this.latitude);
        survivorDto.setLongitude(this.getLongitude());
        survivorDto.setInfectionStatus(this.infectionStatus);
        survivorDto.setInfectionReportCount(this.reporters.size());
        survivorDto.setResources(this.resource.stream().map(Resource::toResourceDto).collect(Collectors.toList()));
        return survivorDto;
    }
}
