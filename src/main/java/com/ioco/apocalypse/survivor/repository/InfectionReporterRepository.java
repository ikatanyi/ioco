package com.ioco.apocalypse.survivor.repository;

import com.ioco.apocalypse.survivor.model.InfectionReporter;
import com.ioco.apocalypse.survivor.model.Survivor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfectionReporterRepository extends JpaRepository<InfectionReporter, Long> {
  List<InfectionReporter>findByInfectedSurvivor(Survivor infectionSurvivor);
}
