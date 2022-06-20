package com.ioco.apocalypse.resource.model.specification;

import com.ioco.apocalypse.resource.model.Resource;
import com.ioco.apocalypse.survivor.model.enumeration.ResourceType;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

/**
 * Filters for searching the data and filtering for the user
 *
 * @author Kelsas
 */
public class ResourceSpecification {

    public ResourceSpecification() {
        super();
    }

    public static Specification<Resource> createSpecification(Long survivorId, ResourceType resourceType, String description) {
        return (root, query, cb) -> {
            final ArrayList<Predicate> predicates = new ArrayList<>();

            if(survivorId!=null) {
                predicates.add(cb.equal(root.get("survivor").get("id"), survivorId));
            }

            if (resourceType!=null) {
                predicates.add(cb.equal(root.get("resourceType"), resourceType ));
            }

            if (description != null) {
                String exp = "%" + description + "%";
                predicates.add(cb.like(root.get("description"), exp));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
