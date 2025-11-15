package com.angeldevs.eventquoteservice.planning.domain.model.queries;

public record GetSocialEventByTitleQuery(String title) {
    /**
     * Constructor for GetSocialEventByTitleQuery.
     *
     * @param title the title to search for
     * @throws IllegalArgumentException if the title is null or empty
     */
    public GetSocialEventByTitleQuery {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
    }
}
