package com.angeldevs.eventquoteservice.planning.interfaces.rest.resources;

import java.time.LocalDate;

public record UpdateSocialEventResource(
        String title,
        LocalDate date,
        String customerName,
        String place,
        String status
) {
    public UpdateSocialEventResource {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (place == null || place.isBlank()) {
            throw new IllegalArgumentException("Place cannot be null or empty");
        }
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        if (customerName == null || customerName.isBlank()) {
            throw new IllegalArgumentException("Customer name cannot be null or empty");
        }
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }
    }
}
