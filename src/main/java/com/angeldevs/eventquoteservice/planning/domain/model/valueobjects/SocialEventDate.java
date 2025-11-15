package com.angeldevs.eventquoteservice.planning.domain.model.valueobjects;

import java.time.LocalDate;

public record SocialEventDate(LocalDate eventDate) {

    /**
     * Constructs a SocialEventDate with the specified event date.
     *
     * @param eventDate the date of the social event, must not be null and cannot be in the past
     * @throws IllegalArgumentException if eventDate is null or in the past
     */

    public SocialEventDate {
        if (eventDate == null) {
            throw new IllegalArgumentException("Event date cannot be null");
        }
        if (eventDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Event date cannot be in the past");
        }
    }

}
