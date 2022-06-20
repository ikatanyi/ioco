package com.ioco.apocalypse.resource.data;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ioco.apocalypse.resource.model.Resource;
import com.ioco.apocalypse.survivor.model.enumeration.ResourceType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.*;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ResourceDto {
    @Schema(hidden = true)
    private Long id;
    private Long survivorId;
    @Enumerated(EnumType.STRING)
    private ResourceType resourceType = ResourceType.OTHER;
    private String description;

    public Resource toResource(){
        Resource resource = new Resource();
        resource.setId(this.getId());
        resource.setResourceType(this.resourceType);
        resource.setDescription(this.getDescription());
        return resource;
    }
}
