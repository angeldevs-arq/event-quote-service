package com.angeldevs.eventquoteservice.planning.domain.model.valueobjects;

/**
 * Enumeration representing the status of a quote
 * @summary
 * This enum define the possible state of a quote.
 * The possible values are:
 * - PENDING: The organizer creates the quote and wait the host's response.
 * - ACCEPTED: The host accept the quote
 * - REJECTED: The host reject the quote
 */

public enum QuoteStatus {
    PENDING,
    ACCEPTED,
    REJECTED
}
