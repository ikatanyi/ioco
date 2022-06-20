package com.ioco.apocalypse.survivor.service;

import com.ioco.apocalypse.exception.APIException;
import com.ioco.apocalypse.survivor.data.InfectionReporterDto;
import com.ioco.apocalypse.survivor.data.SurvivorDto;
import com.ioco.apocalypse.survivor.model.InfectionReporter;
import com.ioco.apocalypse.survivor.model.Survivor;
import com.ioco.apocalypse.survivor.model.SurvivorInterface;
import com.ioco.apocalypse.survivor.model.enumeration.InfectionStatus;
import com.ioco.apocalypse.survivor.repository.InfectionReporterRepository;
import com.ioco.apocalypse.survivor.repository.SurvivorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SurvivorService {
    private final SurvivorRepository survivorRepository;
    private final InfectionReporterRepository infectionReporterRepository;

    public List<Survivor> findAll() {
        return survivorRepository.findAll();
    }

    public Survivor findById(Long id) {
        return survivorRepository.findById(id).orElseThrow(() -> APIException.notFound("Survivor identified by id {0} not found", id));
    }

    public Survivor save(SurvivorDto survivorDto) {
        Survivor survivor = survivorDto.toSurvivor();
        return survivorRepository.save(survivor);
    }

    public Survivor update(Long id, SurvivorDto survivorDto) {
        LocalDate now = LocalDate.now();
        Survivor survivor = survivorRepository.findById(id).orElseThrow(() -> APIException.notFound("Survivor identified by id {0} not found", id));
        survivor.setName(survivorDto.getName());
        survivor.setDob(now.minusYears(survivorDto.getAge()));
        survivor.setGender(survivorDto.getGender());
        survivor.setLatitude(survivorDto.getLatitude());
        survivor.setLongitude(survivorDto.getLongitude());
        return survivorRepository.save(survivor);
    }

    public Survivor updateLocation(Long id, String latitude, String longitude) {
        LocalDate now = LocalDate.now();
        Survivor survivor = survivorRepository.findById(id).orElseThrow(() -> APIException.notFound("Survivor identified by id {0} not found", id));
        survivor.setLatitude(latitude);
        survivor.setLongitude(longitude);
        return survivorRepository.save(survivor);
    }

    public InfectionReporter reportInfection(Long survivorId, InfectionReporterDto infectionReporterDto) {
        Survivor infectedSurvivor = this.findById(survivorId);
        Survivor infectionReporter = this.findById(infectionReporterDto.getInfectionReporterId());
        InfectionReporter reporter = infectionReporterDto.toInfectionReporter();
        reporter.setInfectionReporter(infectionReporter);

        infectedSurvivor.addReport(reporter);
        reporter.setInfectedSurvivor(infectedSurvivor);
        int infectionReportCount = infectedSurvivor.getReporters().size();
        if (infectionReportCount >= 3) {
            infectedSurvivor.setInfectionStatus(InfectionStatus.INFECTED);
        }
        return infectionReporterRepository.save(reporter);
    }

    public SurvivorInterface findInfectionStats(InfectionStatus status){
        SurvivorInterface infectedCount = survivorRepository.findInfectionCount(status.name());
        return infectedCount;
    }

    public List<Survivor> findByInfectionStatus(InfectionStatus status){
        return survivorRepository.findByInfectionStatus(status);
    }

    public void delete(Long id) {
        survivorRepository.deleteById(id);
    }
}
