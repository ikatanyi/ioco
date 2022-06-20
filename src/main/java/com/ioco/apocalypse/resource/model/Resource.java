package com.ioco.apocalypse.resource.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.ioco.apocalypse.resource.data.ResourceDto;
import com.ioco.apocalypse.survivor.model.Survivor;
import com.ioco.apocalypse.survivor.model.enumeration.ResourceType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "resource_inventory")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Resource implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ResourceType resourceType;
    private String description;


    @ManyToOne
    @JoinColumn(name = "survivor_id")
    private Survivor survivor;
    public ResourceDto toResourceDto(){
        ResourceDto resourceDto = new ResourceDto();
        resourceDto.setId(this.getId());
        resourceDto.setSurvivorId(this.getSurvivor().getId());
        resourceDto.setResourceType(this.resourceType);
        resourceDto.setDescription(this.getDescription());
        return resourceDto;
    }
}
