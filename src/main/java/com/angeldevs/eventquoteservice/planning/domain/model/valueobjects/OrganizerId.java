package com.angeldevs.eventquoteservice.planning.domain.model.valueobjects;


import jakarta.persistence.Embeddable;

/**
 * Value object representing the ProfileId for an organizer
 * @summary
 * Represents the id for the profile of an organizer
 * @param profileId The ProfileId for an organizer
 * @see IllegalArgumentException
 */
@Embeddable
public record OrganizerId(String profileId) {
    public OrganizerId{
        if(profileId == null || profileId.isBlank()){
            throw new IllegalArgumentException("Profile id for the organizer cannot be null or blank");
        }
    }
}
