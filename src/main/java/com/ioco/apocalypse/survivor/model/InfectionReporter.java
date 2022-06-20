package com.ioco.apocalypse.survivor.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.ioco.apocalypse.survivor.data.InfectionReporterDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringExclude;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "infection_reporter")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class InfectionReporter implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate reportedDate;

    @ManyToOne
    @JoinColumn(foreignKey =@ForeignKey(name = "FK_infection_reporter_infected_survivor_id"))
    private Survivor infectedSurvivor;

    @ManyToOne
    @JoinColumn(foreignKey =@ForeignKey(name = "FK_infection_reporter_infection_reporter_id"))
    private Survivor infectionReporter;

    public InfectionReporterDto toInfectionReporterDto() {
        InfectionReporterDto dto = new InfectionReporterDto();
        dto.setId(id);
        dto.setReportedDate(reportedDate);
        dto.setInfectionReporterName(infectionReporter.getName());
        dto.setInfectedName(infectedSurvivor.getName());
        dto.setInfectionReporterId(infectionReporter.getId());
        return dto;
    }
}
