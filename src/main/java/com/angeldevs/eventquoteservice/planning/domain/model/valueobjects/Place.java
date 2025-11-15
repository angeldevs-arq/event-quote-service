package com.angeldevs.eventquoteservice.planning.domain.model.valueobjects;

public record Place(String place) {

    /**
     * Constructor for Place value object.
     *
     * @param place the place name, must not be null or blank
     * @throws IllegalArgumentException if the place is null or empty
     */
    public Place {
        if (place == null || place.isBlank()) {
            throw new IllegalArgumentException("Place cannot be null or empty");
        }
    }

}
