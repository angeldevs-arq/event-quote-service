package com.angeldevs.eventquoteservice.planning.domain.model.commands;

/**
 * Command to delete a social event.
 */

public record DeleteSocialEventCommand(Long socialEventId) {

    /**
     * Constructor for DeleteSocialEventCommand.
     *
     * @param socialEventId the ID of the social event to delete
     * @throws IllegalArgumentException if the socialEventId is null or invalid
     */
    public DeleteSocialEventCommand {
        if (socialEventId == null || socialEventId <= 0) {
            throw new IllegalArgumentException("Social event ID must be a positive number");
        }
    }

}
