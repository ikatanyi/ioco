package com.ioco.apocalypse.survivor.repository;

import com.ioco.apocalypse.survivor.model.Survivor;
import com.ioco.apocalypse.survivor.model.SurvivorInterface;
import com.ioco.apocalypse.survivor.model.enumeration.InfectionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SurvivorRepository extends JpaRepository<Survivor, Long> {

    @Query(value="SELECT s.infection_status AS InfectionStatus, ((count(s.id)) * 100.0/ (select count(d.id) FROM survivor d)) AS InfectionPercentage FROM survivor s WHERE s.infection_status = :infectionStatus GROUP BY s.infection_status", nativeQuery = true)
    SurvivorInterface findInfectionCount(@Param("infectionStatus")String infectionStatus);

    List<Survivor> findByInfectionStatus(InfectionStatus infectionStatus);
}
