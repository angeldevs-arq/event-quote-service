package com.angeldevs.eventquoteservice.planning.domain.model.valueobjects;


public record SocialEventTitle(String title) {


    /**
     * Constructor for SocialEventTitle.
     *
     * @param title the title of the social event
     * @throws IllegalArgumentException if the socialEventTitle is null or empty
     */
    public SocialEventTitle {
        if (title == null ) {
            throw new IllegalArgumentException("Social Event Title cannot be null or empty");
        }
    }



}
