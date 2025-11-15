package com.angeldevs.eventquoteservice.planning.domain.model.commands;

import java.util.List;

public record DeleteSocialEventsCommand(List<Long> socialEventIds ) {
    /**
     * Constructor for DeleteSocialEventsCommand.
     *
     * @param socialEventIds the IDs of the social events to delete
     * @throws IllegalArgumentException if the socialEventsIds is null or empty
     */
    public DeleteSocialEventsCommand {
        if (socialEventIds == null || socialEventIds.isEmpty()) {
            throw new IllegalArgumentException("Social event IDs list must not be null or empty");
        }
        if (socialEventIds.stream().anyMatch(id -> id == null || id <= 0)) {
            throw new IllegalArgumentException("All social event IDs must be positive numbers");
        }
    }

}
