package com.angeldevs.eventquoteservice.planning.domain.model.queries;

public record GetSocialEventsByOrganizerQuery(String customerName) {
    /**
     * Constructor for GetSocialEventsByOrganizerQuery.
     *
     * @param customerName the name of the customer/organizer
     * @throws IllegalArgumentException if the customerName is null or empty
     */
    public GetSocialEventsByOrganizerQuery {
        if (customerName == null || customerName.isBlank()) {
            throw new IllegalArgumentException("Customer name cannot be null or empty");
        }
    }
}
