package com.angeldevs.eventquoteservice.planning.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * Value Object representing the profile id for a host
 * @summary
 * Represents the id for the profile of a host
 * @param profileId The profile id for a host
 */
@Embeddable
public record HostId(String profileId) {
    public HostId{
        if(profileId == null || profileId.isBlank())
            throw new IllegalArgumentException("Profile id for the host cannot be null or blank");
    }
}
