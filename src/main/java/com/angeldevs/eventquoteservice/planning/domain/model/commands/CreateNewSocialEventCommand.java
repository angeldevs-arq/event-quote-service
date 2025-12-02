package com.angeldevs.eventquoteservice.planning.domain.model.commands;

/**
 * Command to request the creation of a Social Event.
 *
 * @param title        the title of the event
 * @param place        the location of the event
 * @param date         the date the event will take place
 * @param customerName the name of the event organizer
 * @param status       the initial status (e.g., ACTIVE, TO_CONFIRM)
 */

public record CreateNewSocialEventCommand(
        String title,
        java.util.Date date,
        String customerName,
        String place,
        String status
) {

    public CreateNewSocialEventCommand {
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
