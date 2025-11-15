package com.angeldevs.eventquoteservice.planning.domain.model.queries;

public record GetSocialEventByStatusQuery(String status) {
    /**
     * Constructor for GetSocialEventByStatusQuery.
     *
     * @param status the status to filter by
     * @throws IllegalArgumentException if the status is null or empty
     */
    public GetSocialEventByStatusQuery {
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }
    }
}
