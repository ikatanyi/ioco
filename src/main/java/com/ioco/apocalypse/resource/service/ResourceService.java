package com.ioco.apocalypse.resource.service;

import com.ioco.apocalypse.exception.APIException;
import com.ioco.apocalypse.resource.data.ResourceDto;
import com.ioco.apocalypse.resource.model.Resource;
import com.ioco.apocalypse.survivor.model.Survivor;
import com.ioco.apocalypse.survivor.model.enumeration.ResourceType;
import com.ioco.apocalypse.resource.model.specification.ResourceSpecification;
import com.ioco.apocalypse.resource.repository.ResourceRepository;
import com.ioco.apocalypse.survivor.service.SurvivorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author Kennedy Ikatanyi
 */
@Slf4j
@Service
@RequiredArgsConstructor
//@CacheConfig(cacheNames = {"Resource"})
public class ResourceService {

    private final ResourceRepository resourceRepository;
    private final SurvivorService survivorService;

//
    @Transactional
    public Resource createResource(ResourceDto resourceDto) {
        Survivor survivor = survivorService.findById(resourceDto.getSurvivorId());
        Resource resource = resourceDto.toResource();
        resource.setSurvivor(survivor);
        return resourceRepository.save(resource);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Resource updateItem(Long id, ResourceDto resourceData) {
        Resource resource = fetchResourceOrThrow(id);
        resource.setResourceType(resourceData.getResourceType());
        resource.setDescription(resourceData.getDescription());
        return resourceRepository.save(resource);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Boolean deleteResource(Long id) {
        Resource resource = fetchResourceOrThrow(id);
        resourceRepository.delete(resource);
        return true;
    }

    public Page<Resource> fetchResources(Long survivorId, ResourceType resourceType, String description,  Pageable pageable) {
        Specification<Resource> spec = ResourceSpecification.createSpecification(survivorId, resourceType, description);
        Page<Resource> items = resourceRepository.findAll(spec, pageable);
        return items;
    }

    public List<Resource> fetchSurvivorResources(Long survivorId) {
        fetchResourceOrThrow(survivorId);
        return resourceRepository.findBySurvivor_id(survivorId);
    }


    public Resource fetchResourceOrThrow(Long id) {
        return this.resourceRepository.findById(id)
                .orElseThrow(() -> APIException.notFound("Resource id {0} not found.", id));
    }

    @Transactional(readOnly = true)
    @Cacheable
    public Collection<Resource> findAll() {
        return resourceRepository.findAll();
    }


}
