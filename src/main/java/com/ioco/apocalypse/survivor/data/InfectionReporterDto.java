package com.ioco.apocalypse.survivor.data;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ioco.apocalypse.survivor.model.InfectionReporter;
import com.ioco.apocalypse.survivor.model.Survivor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class InfectionReporterDto  {
    @Schema(hidden = true)
    private Long id;
    private LocalDate reportedDate = LocalDate.now();
    @Schema(hidden = true)
    private String infectionReporterName;
    @Schema(hidden = true)
    private String infectedName;
    private Long infectionReporterId;

    public InfectionReporter toInfectionReporter() {
        InfectionReporter reporter = new InfectionReporter();
        reporter.setReportedDate(reportedDate);
        return reporter;
    }
}
