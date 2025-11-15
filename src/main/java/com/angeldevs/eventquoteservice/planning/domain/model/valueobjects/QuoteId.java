package com.angeldevs.eventquoteservice.planning.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.util.UUID;


/**
 * Value object representing a unique identifier for a quote
 * @summary
 * Represents a unique identifier of a quote.
 * It's used to reference quotes withing the system
 * @param quoteId the unique identifier for a quote.
 * @see IllegalArgumentException
 */
@Embeddable
public record QuoteId(String quoteId) {
    public QuoteId(){
        this(UUID.randomUUID().toString());
    }

    public QuoteId{
        if(quoteId == null || quoteId.isBlank())
            throw new IllegalArgumentException("Quote id cannot be null or blank");
    }
}
