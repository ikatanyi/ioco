package com.ioco.apocalypse.survivor.repository;

import com.ioco.apocalypse.survivor.model.InfectionReporter;
import com.ioco.apocalypse.survivor.model.Survivor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfectionReporterRepository extends JpaRepository<InfectionReporter, Long> {

}
