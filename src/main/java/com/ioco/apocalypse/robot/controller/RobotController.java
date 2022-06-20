package com.ioco.apocalypse.robot.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ioco.apocalypse.resource.data.ResourceDto;
import com.ioco.apocalypse.resource.model.Resource;
import com.ioco.apocalypse.resource.service.ResourceService;
import com.ioco.apocalypse.robot.config.ResponseMapper;
import com.ioco.apocalypse.robot.data.RobotDto;
import com.ioco.apocalypse.robot.service.RobotService;
import com.ioco.apocalypse.survivor.model.enumeration.ResourceType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 *
 * @author Kelsas
 */
@RestController
@Slf4j
@RequestMapping("/api/robot")
@RequiredArgsConstructor
public class RobotController {

    private final RobotService service;



    @GetMapping
    public ResponseEntity<?> getResourceById() throws JsonProcessingException {
        List<RobotDto> dto = service.fetchRobots();
        return ResponseEntity.ok(dto);
    }


}
