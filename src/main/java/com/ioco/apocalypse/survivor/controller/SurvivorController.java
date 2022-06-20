package com.ioco.apocalypse.survivor.controller;

import com.ioco.apocalypse.survivor.data.InfectionReporterDto;
import com.ioco.apocalypse.survivor.data.LocationDto;
import com.ioco.apocalypse.survivor.data.SurvivorDto;
import com.ioco.apocalypse.survivor.model.InfectionReporter;
import com.ioco.apocalypse.survivor.model.Survivor;
import com.ioco.apocalypse.survivor.model.enumeration.InfectionStatus;
import com.ioco.apocalypse.survivor.service.SurvivorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/survivor")
@RequiredArgsConstructor
public class SurvivorController {
    private final SurvivorService survivorService;

    @PostMapping
    public ResponseEntity<?> createSurvor(@Valid @RequestBody SurvivorDto survivorDto) {

        SurvivorDto savedSurvivorDto = survivorService.save(survivorDto).toSurvivorDto();
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSurvivorDto);
    }

    @GetMapping
    public ResponseEntity<?> findAllSurvivors() {
        return ResponseEntity.ok(survivorService.findAll().stream().map(Survivor::toSurvivorDto).collect(Collectors.toList()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSurvivor(@PathVariable(value = "id") Long id, @Valid @RequestBody SurvivorDto survivorDto) {
        return ResponseEntity.ok(survivorService.update(id, survivorDto).toSurvivorDto());
    }

    @PutMapping("/{id}/last-location")
    public ResponseEntity<?> updateSurvivorLastLocation(@PathVariable(value = "id") Long id, @Valid @RequestBody LocationDto locationDto)  {
        return ResponseEntity.status(HttpStatus.OK).body(survivorService.updateLocation(id, locationDto).toSurvivorDto());
    }

    @PostMapping("/{id}/infection-reporter")
    public ResponseEntity<?> reportInfection(@PathVariable(value = "id") Long id, @Valid @RequestBody InfectionReporterDto infectionReporterDto) {

        InfectionReporterDto savedSurvivorDto = survivorService.reportInfection(id, infectionReporterDto).toInfectionReporterDto();
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSurvivorDto);
    }

    @GetMapping("/{survivorId}/infection-reporters")
    public ResponseEntity<?> findInfectionReporters(@PathVariable(value = "survivorId") Long survivorId) {

        List<InfectionReporterDto> reporters = survivorService.findInfectionReporter(survivorId).stream().map(InfectionReporter::toInfectionReporterDto).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.CREATED).body(reporters);
    }

    @GetMapping("/infection-stats")
    public ResponseEntity<?> reportInfectionStats(@RequestParam(value = "infectionStatus", required = true) InfectionStatus infectionStatus ) {
        return ResponseEntity.ok(survivorService.findInfectionStats(infectionStatus));
    }

    @GetMapping("/infection/list")
    public ResponseEntity<?> listSurvivorByInfection(@RequestParam(value = "infectionStatus", required = true) InfectionStatus infectionStatus ) {
        return ResponseEntity.ok(survivorService.findByInfectionStatus(infectionStatus).stream().map(Survivor::toSurvivorDto).collect(Collectors.toList()));
    }


}
