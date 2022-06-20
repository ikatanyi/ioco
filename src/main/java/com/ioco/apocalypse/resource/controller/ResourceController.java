package com.ioco.apocalypse.resource.controller;

import com.ioco.apocalypse.resource.data.ResourceDto;
import com.ioco.apocalypse.resource.model.Resource;
import com.ioco.apocalypse.resource.service.ResourceService;
import com.ioco.apocalypse.survivor.model.enumeration.ResourceType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Kelsas
 */
@RestController
@Slf4j
@RequestMapping("/api/resource")
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceService service;


    @PostMapping
    public ResponseEntity<?> createResource(@Valid @RequestBody ResourceDto resourceDto) {
        ResourceDto dto = service.createResource(resourceDto).toResourceDto();
        return ResponseEntity.ok(dto);

    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getResourceById(@PathVariable(value = "id") Long id) {
        ResourceDto dto = service.fetchResourceOrThrow(id).toResourceDto();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/survivor/{survivorId}")
    public ResponseEntity<?> getResourceBySurvivor(@PathVariable(value = "survivorId") Long survivorId) {
        List<ResourceDto> resources = service.fetchSurvivorResources(survivorId).stream().map(Resource::toResourceDto).collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateResource(@PathVariable(value = "id") Long id, @Valid @RequestBody ResourceDto resourceDto) {

        Resource resource = service.updateItem(id, resourceDto);
        return ResponseEntity.ok(resource.toResourceDto());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteResource(@PathVariable(value = "id") Long id) {

        Boolean isDeleted = service.deleteResource(id);
        return ResponseEntity.ok(isDeleted);

    }

    @GetMapping
    public ResponseEntity<?> getAllResources(
            @RequestParam(value = "resourceType", required = false) final ResourceType resourceType,
            @RequestParam(value = "survivorId", required = false) final Long survivorId,
            @RequestParam(value = "description", required = false) final String description,
            @RequestParam(value = "page", defaultValue = "0",required = false) Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20",required = false) Integer size) {
        page = page>=1 ? page-1 : page;
        Pageable pageable = PageRequest.of(page, size);

        Page<ResourceDto> pagedList = service.fetchResources(survivorId, resourceType, description, pageable).map(u -> u.toResourceDto());
        return ResponseEntity.ok(pagedList);
    }

}
