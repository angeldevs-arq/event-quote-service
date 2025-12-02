package com.angeldevs.eventquoteservice.planning.domain.model.commands;

import java.time.LocalDate;
import java.util.Date;

/**
 * Command to update an existing social event.
 */

public record UpdateSocialEventCommand( Long socialEventId,
                                        String title,
                                        Date date,
                                        String customerName,
                                        String place,
                                        String status ) {

    /**
     * Constructor for UpdateSocialEventCommand.
     *
     * @param socialEventId the ID of the social event to update
     * @param title         the new title of the event
     * @param date          the new scheduled date
     * @param customerName  the name of the event organizer
     * @param place         the new location of the event
     * @param status        the new status of the event
     */
    public UpdateSocialEventCommand {
        if (socialEventId == null || socialEventId <= 0) {
            throw new IllegalArgumentException("Social event ID must be a positive number");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        if (customerName == null || customerName.isBlank()) {
            throw new IllegalArgumentException("Customer name cannot be null or empty");
        }
        if (place == null || place.isBlank()) {
            throw new IllegalArgumentException("Place cannot be null or empty");
        }
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }
    }

}
