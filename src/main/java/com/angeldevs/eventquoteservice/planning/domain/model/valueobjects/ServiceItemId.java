package com.angeldevs.eventquoteservice.planning.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.util.UUID;

/**
 * Value object representing a unique identifier for a service item
 * @summary
 * Represents a unique identifier of a service item.
 * It's used to reference quotes withing the system
 * @param serviceItemId the unique identifier for a service item.
 * @see IllegalArgumentException
 */
@Embeddable
public record ServiceItemId(String serviceItemId) {
    public ServiceItemId(){this(UUID.randomUUID().toString());}

    public ServiceItemId{
        if(serviceItemId == null || serviceItemId.isBlank())
            throw new IllegalArgumentException("serviceItemId cannot be null or blank");
    }
}
